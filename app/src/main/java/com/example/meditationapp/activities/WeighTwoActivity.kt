package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import com.example.meditationapp.javaActivities.WeightActivityNew
import kotlinx.android.synthetic.main.tool_bar_two.*
import kotlinx.android.synthetic.main.weight_two_fragment.*

class WeighTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_two_fragment)
        ll_wl_my_recordings.setOnClickListener(View.OnClickListener {
//            val weightFragment = WeightFragment.newInstance()
//            openFragment(weightFragment)
                        val intent = Intent(this@WeighTwoActivity, WeightActivityNew::class.java)
            startActivity(intent)

        })

//        img_back_two.setOnClickListener(View.OnClickListener {
////            val intent = Intent(this@WeighTwoActivity, HomeActivity::class.java)
////            // val intent = Intent(this, VideocallingActivity::class.java)
////            startActivity(intent)
//            finish()
//        })
//        img_abun.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@WeighTwoActivity, AbundanceTwoActivity::class.java)
//            startActivity(intent)
//
//        })
//        img_health.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@WeighTwoActivity, HealthTwoActivity::class.java)
//            startActivity(intent)
//
//        })
//        img_one_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, CreativtyAffirmationsActivity::class.java)
//            startActivity(voice)
//        })
//
//        img_two_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        img_three_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        img_four_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        img_five_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        img_six_weight.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@WeighTwoActivity, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//    }
//    private fun openFragment(fragment: Fragment) {
//        val transaction =supportFragmentManager?.beginTransaction()
//        transaction?.replace(com.example.meditationapp.R.id.container, fragment)
//        transaction?.addToBackStack(null)
//        transaction?.commit()
    }
}
