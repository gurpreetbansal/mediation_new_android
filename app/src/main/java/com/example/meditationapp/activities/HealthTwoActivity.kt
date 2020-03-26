package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.health_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class HealthTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.health_two_fragment)
        ll_h_my_recodrings.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@HealthTwoActivity, HealthActivity::class.java)
            startActivity(intent)
        })


        img_back_two.setOnClickListener(View.OnClickListener {
           // val intent = Intent(this@HealthTwoActivity, HomeActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            //startActivity(intent)
            finish()
        })
        img_abun_health.setOnClickListener(View.OnClickListener {
//            val weightFragment = AbundanceTwoFragment.newInstance()
//            openFragment(weightFragment)
            val intent = Intent(this@HealthTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)
        })
        img_health_health.setOnClickListener(View.OnClickListener {
           // val weightFragment = HealthTwoFragment.newInstance()
           // openFragment(weightFragment)
            val intent = Intent(this@HealthTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)

        })

        img_health_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_health_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_health_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_health_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_health_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_health_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@HealthTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })

    }
    private fun openFragment(fragment: Fragment) {
        val transaction =supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
