package com.example.sweetflowershop.ui.view.main

import CategoryFragment
import com.example.sweetflowershop.ui.view.product.HomeFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.sweetflowershop.R
import com.example.sweetflowershop.ui.view.account.AccountFragment
import com.example.sweetflowershop.ui.view.login.LoginActivity
import com.example.sweetflowershop.ui.view.notification.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pagerAdapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    saveTokenToSharedPreferences(token)
                    Log.d("FCM Token", token ?: "Token is null")
                } else {
                    Log.e("FCM Token", "Failed to get token")
                }
            }
        val shouldLaunchLoginActivity = intent.getBooleanExtra("shouldLaunchLoginActivity", true)

        if (shouldLaunchLoginActivity) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            setContentView(R.layout.activity_main)
            supportActionBar?.hide()

            viewPager = findViewById(R.id.view_pager)
            bottomNavigationView = findViewById(R.id.bottom_nav)
            pagerAdapter = MyPagerAdapter(supportFragmentManager)
            viewPager.adapter = pagerAdapter

            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_home -> viewPager.currentItem = 0
                    R.id.action_category -> viewPager.currentItem = 1
                    R.id.action_account -> viewPager.currentItem = 2
                    R.id.action_notification -> viewPager.currentItem = 3
                }
                true
            }
        }

    }

    private fun saveTokenToSharedPreferences(token: String?) {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("FCM_TOKEN", token)
        editor.apply()
    }

    inner class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment()
                1 -> CategoryFragment()
                2 -> AccountFragment()
                3 -> NotificationFragment()
                else -> HomeFragment()
            }
        }

        override fun getCount(): Int {
            return 4
        }
    }
}
