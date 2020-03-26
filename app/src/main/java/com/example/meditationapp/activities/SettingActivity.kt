package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import com.example.meditationapp.javaActivities.AccountSettingActivityNew
import kotlinx.android.synthetic.main.account_fragment.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_fragment)
        txt_account.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, AccountSettingActivityNew::class.java)
            startActivity(intent)
        })
        img_account_first_back.setOnClickListener(View.OnClickListener {
            finish()
        })
        txt_sub.setOnClickListener(View.OnClickListener {
//            val weightFragment = AccountThreeFragment .newInstance()
////            openFragment(weightFragment)
            finish()
        })
        txt_notification.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, NotificationActivity::class.java)
            startActivity(intent)
        })
        txt_terms_condition.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, Terms_And_Conditions_Acxtivity::class.java)
            startActivity(intent)
        })
        txt_support.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, SupportActivity::class.java)
            startActivity(intent)
        })
        txt_privacy.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, PrivacyActivity::class.java)
            startActivity(intent)
        })

        txt_logout.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, LogOut_Activity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
            // activity!!.finish()

        })


        txt_help_center.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SettingActivity, HelpCenter_Activity::class.java)
            startActivity(intent)

        })
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
