package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.stress_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class StressTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stress_two_fragment)
        ll_s_mr.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StressTwoActivity, StressActivity::class.java)
            startActivity(intent)
        })


        img_back_two.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@StressTwoActivity, HomeActivity::class.java)
//            // val intent = Intent(this, VideocallingActivity::class.java)
//            startActivity(intent)
            finish()
        })

        img_abun_stress.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StressTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)
        })
        img_health_stress.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@StressTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)
        })


        img_stress_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_stress_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_stress_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_stress_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_stress_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_stress_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@StressTwoActivity, GetMore_Activity::class.java)
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
