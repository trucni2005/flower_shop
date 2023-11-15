package com.example.sweetflowershop.ui.view.account

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sweetflowershop.R
import com.example.sweetflowershop.data.model.customer_account.Account
import com.example.sweetflowershop.data.model.customer_account.AccountInformation
import com.example.sweetflowershop.databinding.ActivityCustomerInformationBinding
import com.example.sweetflowershop.network.apiService.account.AccountAPIService
import com.example.sweetflowershop.ui.view.main.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomerInformationActivity : AppCompatActivity() {
    private val accountAPIService = AccountAPIService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ẩn ActionBar
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        // Nhận dữ liệu Account từ Intent
        val account: Account? = intent.getSerializableExtra("account") as? Account
        Log.d("account", account.toString())

        // DataBinding
        val binding: ActivityCustomerInformationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_customer_information)

        // Gán Account vào DataBinding
        binding.account = account

        // Hiển thị DatePickerDialog khi người dùng nhấp vào ô nhập ngày sinh
        binding.etBirthday.setOnClickListener {
            showDatePickerDialog(binding)
        }

        // Xử lý sự kiện khi người dùng chọn giới tính Nam
        binding.rbMale.setOnClickListener {
            if (!binding.rbMale.isChecked) {
                binding.rbMale.isChecked = true
                binding.rbFemale.isChecked = false
            }
        }

        // Xử lý sự kiện khi người dùng chọn giới tính Nữ
        binding.rbFemale.setOnClickListener {
            if (!binding.rbFemale.isChecked) {
                binding.rbFemale.isChecked = true
                binding.rbMale.isChecked = false
            }
        }

        // Xử lý sự kiện khi người dùng nhấn nút Lưu
        binding.btnSave.setOnClickListener {
            // Lấy dữ liệu từ các trường nhập liệu
            val phone = binding.etPhone.text.toString()
            val fullName = binding.etFullname.text.toString()
            val email = binding.etEmail.text.toString()

            // Lấy giới tính dựa trên RadioButton
            val sex = binding.rbMale.isChecked

            // Lấy ngày sinh từ EditText
            val birth = binding.etBirthday.text.toString()

            // Gọi hàm cập nhật thông tin tài khoản
            updateAccountInformation(phone, fullName, email, sex, birth)
        }
    }

    // Hàm hiển thị DatePickerDialog
    private fun showDatePickerDialog(binding: ActivityCustomerInformationBinding) {
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

                // Đặt giá trị ngày sinh vào EditText
                binding.etBirthday.setText(formattedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
    }

    // Hàm cập nhật thông tin tài khoản
    private fun updateAccountInformation(phone: String, fullName: String, email: String, sex: Boolean, birthday: String) {
        val sharedPreferences =
            this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Authorization", null)

        // Tạo đối tượng AccountInformation để gửi lên server
        val accountInformation = AccountInformation(phone, fullName, email, sex, birthday)

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
