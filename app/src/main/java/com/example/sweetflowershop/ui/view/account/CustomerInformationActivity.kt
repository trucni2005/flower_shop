package com.example.sweetflowershop.ui.view.account

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.data.model.customer_account.AccountInformation
import com.example.sweetflowershop.databinding.ActivityCustomerInformationBinding
import com.example.sweetflowershop.network.apiService._Constant
import com.example.sweetflowershop.data.repository.AccountRepository
import com.example.sweetflowershop.ui.view.main.MainActivity
import com.example.sweetflowershop.ui.viewmodel.AccountViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomerInformationActivity : AppCompatActivity() {
    private val accountAPIService = AccountRepository()
    private lateinit var binding: ActivityCustomerInformationBinding
    private val compositeDisposable = CompositeDisposable()
    private val CAMERA_AND_GALLERY_PERMISSION_REQUEST = 300
    private val PICK_IMAGE_REQUEST = 1
    private var base64Image: ByteArray? = null
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer_information)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        val account: Account? = intent.getSerializableExtra("account") as? Account
        Log.d("account", account.toString())
        account?.let {
            binding.account = it

            val fullUrl = _Constant.baseUrl_ + "images/customer/" + it.avatar
            Log.d("fullUrl", fullUrl)
            Picasso.get()
                .load(fullUrl)
                .into(binding.ivUserPhoto)
        }

        binding.etBirthday.setOnClickListener {
            showDatePickerDialog()
        }

        binding.rbMale.setOnClickListener {
            if (!binding.rbMale.isChecked) {
                binding.rbMale.isChecked = true
                binding.rbFemale.isChecked = false
            }
        }

        binding.rbFemale.setOnClickListener {
            if (!binding.rbFemale.isChecked) {
                binding.rbFemale.isChecked = true
                binding.rbMale.isChecked = false
            }
        }

        binding.btnSave.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            val fullName = binding.etFullname.text.toString()
            val email = binding.etEmail.text.toString()
            val sex = binding.rbMale.isChecked
            val birth = binding.etBirthday.text.toString()
            if (base64Image != null) {
                updateAccountInformation(base64Image!!, phone, fullName, email, sex, birth)
            } else {
                updateAccountInformation(null, phone, fullName, email, sex, birth)
            }
        }

        binding.avatarInformation.setOnClickListener {
            requestCameraAndGalleryPermissions()
        }
    }

        private fun requestCameraAndGalleryPermissions() {
            val cameraPermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

            val galleryPermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED

            val permissionsToRequest = ArrayList<String>()

            if (!cameraPermission) {
                permissionsToRequest.add(Manifest.permission.CAMERA)
            }

            if (!galleryPermission) {
                permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

            if (permissionsToRequest.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toTypedArray(),
                    CAMERA_AND_GALLERY_PERMISSION_REQUEST
                )
            } else {
                openGallery()
                Log.d("Permission", "Both permissions already granted")
            }
        }

        private fun openGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        private fun encodeImageToBase64(bitmap: Bitmap): ByteArray? {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
            return byteArrayOutputStream.toByteArray()
        }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
                val selectedImageUri: Uri? = data?.data

                try {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                    base64Image = encodeImageToBase64(bitmap)

                    Log.d("base64Image", base64Image.toString())

                    Glide.with(this)
                        .asBitmap()
                        .load(bitmap)
                        .apply(RequestOptions().dontTransform())
                        .into(binding.ivUserPhoto)

                    Toast.makeText(this, "Upload avatar successful!", Toast.LENGTH_SHORT).show()

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error processing image", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun fetchAccount() {
        accountViewModel.fetchAccount(this)
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.etBirthday.setText(formattedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
    }

    private fun updateAccountInformation(
            avatar: ByteArray?,
            phone: String,
            fullName: String,
            email: String,
            sex: Boolean,
            birthday: String
        ) {
            val sharedPreferences =
                this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("Authorization", null)

            Log.d("avatar", avatar.toString())
            val accountInformation =
                AccountInformation(avatar, phone, fullName, email, sex, birthday)
            Log.d("accountInformation", accountInformation.toString())

            if (token != null) {
                accountAPIService.updateAccountInformation(token, accountInformation)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.let {
                        compositeDisposable.add(
                            it.subscribe(
                                { accountModel ->
                                    if (accountModel.success) {
                                        val successMessage = accountModel.message
                                        Log.d("DEBUG", "Success. Message: $successMessage")
                                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                                        navigateToMainActivity()

                                    } else {
                                        Log.e("ERROR", "Error: ${accountModel.message}")
                                    }
                                },
                                { error ->
                                    Log.e("ERROR", "Error: ${error.message}")
                                }
                            )
                        )
                    }
            }
        }

        private fun navigateToMainActivity() {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("shouldLaunchLoginActivity", false)
            startActivity(intent)
        }

        override fun onDestroy() {
            super.onDestroy()
            compositeDisposable.clear()
        }
    }

