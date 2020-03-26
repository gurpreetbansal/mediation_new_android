package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.getmore_activity.*

class GetMore_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getmore_activity)

        txt_terms_get.setOnClickListener(View.OnClickListener {
            val voice = Intent(this@GetMore_Activity, Terms_And_Conditions_Acxtivity::class.java)
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
