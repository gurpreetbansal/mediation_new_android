package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.quit_activity.*

class QuitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quit_activity)
        txt_help_center_quit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HelpCenter_Activity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
            finish()
        })
        txt_no.setOnClickListener(View.OnClickListener {

            finish()
        })
        txt_yes.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}
