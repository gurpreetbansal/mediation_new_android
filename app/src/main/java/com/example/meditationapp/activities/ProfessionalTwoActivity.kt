package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.professional_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class ProfessionalTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.professional_two_fragment)
        ll_pro_mr.setOnClickListener(View.OnClickListener {
//            val weightFragment = ProfessionalFragment.newInstance()
//            openFragment(weightFragment)
            val intent = Intent(this@ProfessionalTwoActivity, ProfessionalActivity::class.java)
            startActivity(intent)
        })
        img_back_two.setOnClickListener(View.OnClickListener {
            finish()
//            val intent = Intent(this@ProfessionalTwoActivity, HomeActivity::class.java)
//            // val intent = Intent(this, VideocallingActivity::class.java)
//            startActivity(intent)
        })
        img_abun_pro.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ProfessionalTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)
        })
        img_health_pro.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ProfessionalTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)
        })



        img_profesion_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_profesion_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_profesion_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_profesion_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_profesion_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_profesion_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@ProfessionalTwoActivity, GetMore_Activity::class.java)
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
