package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.relationship_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*

class RelationshipTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.relationship_two_fragment)
        ll_relation_mr.setOnClickListener(View.OnClickListener {
           // val weightFragment = RelationshipFragment.newInstance()
            //openFragment(weightFragment)
            val intent = Intent(this@RelationshipTwoActivity, RelationshipActivity::class.java)
            startActivity(intent)
        })


        img_back_two.setOnClickListener(View.OnClickListener {
           // val intent = Intent(this@RelationshipTwoActivity, HomeActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            //startActivity(intent)
            finish()
        })

        img_abun_rel.setOnClickListener(View.OnClickListener {
//            val weightFragment = AbundanceTwoFragment.newInstance()
//            openFragment(weightFragment)
            val intent = Intent(this@RelationshipTwoActivity, AbundanceTwoActivity::class.java)
            startActivity(intent)
        })
        img_health_rel.setOnClickListener(View.OnClickListener {
//            val weightFragment = HealthTwoFragment.newInstance()
//            openFragment(weightFragment)
            val intent = Intent(this@RelationshipTwoActivity, HealthTwoActivity::class.java)
            startActivity(intent)
        })


        img_relation_one.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_relation_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_relation_three.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_relation_four.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_relation_five.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_relation_six.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@RelationshipTwoActivity, GetMore_Activity::class.java)
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
