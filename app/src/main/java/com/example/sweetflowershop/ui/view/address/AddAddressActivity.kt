package com.example.sweetflowershop.ui.view.address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetflowershop.data.model.address.AddressToSend
import com.example.sweetflowershop.data.model.address.City
import com.example.sweetflowershop.data.model.address.District
import com.example.sweetflowershop.data.model.address.Ward
import com.example.sweetflowershop.databinding.ActivitySetAddressBinding
import com.example.sweetflowershop.data.repository.AddressAPIService
import com.example.sweetflowershop.data.repository.CityRepository
import com.example.sweetflowershop.ui.view.login.LoginActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class AddAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetAddressBinding
    private val cityAPIService = CityRepository()
    private val addressAPIService = AddressAPIService()
    private val cityList: MutableList<City> = mutableListOf()
    private val compositeDisposable = CompositeDisposable()
    private var selectedCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetAddressBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val nameEditText = binding.tvName
        val phoneEditText = binding.tvPhone
        val districtSpinner = binding.districtSpinnerCardAddBottomSheet
        val wardSpinner = binding.wardSpinnerCardAddBottomSheet
        val addressEditText = binding.addressCardAddBottomSheet
        val addButton = binding.addCardBtnCardAddBottomSheet

        val citySpinner = binding.citySpinnerCardAddBottomSheet
        loadCities(citySpinner)

        citySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                selectedCity = cityList[position]
                updateDistrictSpinner(districtSpinner, selectedCity?.districts.orEmpty())
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        })

        districtSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedDistrict = selectedCity?.districts?.get(position)
                updateWardSpinner(wardSpinner, selectedDistrict?.wards.orEmpty())
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        })

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val city = citySpinner.selectedItem.toString()
            val district = districtSpinner.selectedItem.toString()
            val ward = wardSpinner.selectedItem.toString()
            val street = addressEditText.text.toString()

            val addresstoSend= AddressToSend(name, phone, city, district, ward, street)

            Log.d(
                "AddressInfo",
                "Name: $name, Phone: $phone, City: $city, District: $district, Ward: $ward, Address: $street"
            )

            val sharedPreferences =
                this.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("Authorization", null)
            Log.d("token", token.toString())

            if (!token.isNullOrEmpty()) {
                val addToCartObservable = addressAPIService.addAddress(token, addresstoSend)

                addToCartObservable
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                        { accountModel ->
                            if (accountModel.success) {
                                Toast.makeText(this, "Add new address success", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, ChooseAddressActivity::class.java)
                                this.startActivity(intent)
                            } else {
                                Log.e("Test", "Failed: ${accountModel.message}")
                            }
                        },
                        { error ->
                            Log.e("Test", "Error: $error")
                        }
                    )?.let {
                        compositeDisposable.add(it)
                    }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                this.startActivity(intent)
            }
        }
    }

    private fun loadCities(citySpinner: Spinner) {
        cityAPIService.fetchCities(
            onSuccess = { cities ->
                cityList.clear()
                cityList.addAll(cities)
                updateCitySpinner(citySpinner)
            },
            onError = { error ->
                error.printStackTrace()
            }
        )
    }

    private fun updateCitySpinner(citySpinner: Spinner) {
        val cityNames = cityList.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityNames)
        citySpinner.adapter = adapter
    }

    private fun updateDistrictSpinner(districtSpinner: Spinner, districts: List<District>) {
        val districtNames = districts.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, districtNames)
        districtSpinner.adapter = adapter
    }

    private fun updateWardSpinner(wardSpinner: Spinner, wards: List<Ward>) {
        val wardNames = wards.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wardNames)
        wardSpinner.adapter = adapter
    }
}