package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.activity_two_athletic.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class AthleicTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_athletic)
        ll_ath_mr.setOnClickListener(View.OnClickListener {
            //val weightFragment = RelationshipFragment.newInstance()
            //openFragment(weightFragment)
            val intent = Intent(this@AthleicTwoActivity, AthleticActivity::class.java)
            startActivity(intent)
        })


        img_back_two.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@AthleicTwoActivity, HomeActivity::class.java)
//            // val intent = Intent(this, VideocallingActivity::class.java)
//            startActivity(intent)
            finish()
        })

        img_ath_abun.setOnClickListener(View.OnClickListener {
//            val weightFragment = AbundanceTwoFragment.newInstance()
//            openFragment(weightFragment)
            val intent = Intent(this@AthleicTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)

        })
        img_ath_rel.setOnClickListener(View.OnClickListener {
//            val weightFragment = HealthTwoFragment.newInstance()
//            openFragment(weightFragment)

            val intent = Intent(this@AthleicTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)
        })


        img_ath_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_ath_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_ath_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_ath_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_ath_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_ath_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@AthleicTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}