package com.example.meditationapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.meditationapp.R
import com.example.meditationapp.javaActivities.LoginActivityNew
import com.facebook.FacebookCallback

class Splash_Activity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000
    private var handler = Handler()
    val mypreference = "mypref"
    val user_id = "user_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStatusBaseWhiteMain();
        val window = this.window
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

// finally change the color
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        setContentView(R.layout.splash_activity)

        val pref = getSharedPreferences(
            mypreference,
            Context.MODE_PRIVATE
        )
//        Toast.makeText(this,pref.getString(user_id, ""), Toast.LENGTH_SHORT).show()
        if (pref.getString(user_id, "")=="") {
            handler.postDelayed(
                {
                    val intent = Intent(this, LoginActivityNew::class.java)
                    startActivity(intent)
                    finish()

                }, SPLASH_TIME_OUT.toLong()
            )
        }else{
            handler.postDelayed(
                {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                }, SPLASH_TIME_OUT.toLong()
            )
        }

//        profile = Profile.getCurrentProfile().getCurrentProfile();
//        if (profile != null) {
//            // user has logged in
//        } else {
//            // user has not logged in
//        }



    }
}
