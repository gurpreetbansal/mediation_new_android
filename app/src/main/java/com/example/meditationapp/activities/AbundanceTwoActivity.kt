package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.abun_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class AbundanceTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abun_two_fragment)
        ll_abun_mr.setOnClickListener(View.OnClickListener {
           // val weightFragment = AbundanceFragment.newInstance()
           // openFragment(weightFragment)
                        val intent = Intent(this@AbundanceTwoActivity, AbundanceActivity::class.java)
            startActivity(intent)


        })
        img_back_two.setOnClickListener(View.OnClickListener {
            finish()
//            val intent = Intent(this@AbundanceTwoActivity, HomeActivity::class.java)
////            // val intent = Intent(this, VideocallingActivity::class.java)
////            startActivity(intent)
        })

        img_abun_abun.setOnClickListener(View.OnClickListener {
           // val weightFragment = AbundanceTwoFragment.newInstance()
           // openFragment(weightFragment)
            val intent = Intent(this@AbundanceTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)
        })
        img_health_abun.setOnClickListener(View.OnClickListener {
          //  val weightFragment = HealthTwoFragment.newInstance()
          //  openFragment(weightFragment)
            val intent = Intent(this@AbundanceTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)
        })

        img_abundance_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_abundance_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_abundance_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_abundance_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_abundance_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_abundance_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AbundanceTwoActivity, GetMore_Activity::class.java)
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
