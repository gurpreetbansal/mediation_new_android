package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meditationapp.R


class CongratulationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.congratulation_activity)
    }
    override fun onBackPressed() {
        val terms = Intent(this@CongratulationActivity, HomeActivity::class.java)
        startActivity(terms)
    }
}
