package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txt_sign_up.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUp_Activity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
            finish()
        })
        login__txt_log_in.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}
