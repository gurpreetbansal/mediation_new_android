package com.example.meditationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.privacy_fragment.*

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_fragment)
        img_privacy_back.setOnClickListener(View.OnClickListener {
           finish()
        })
//        webview_p.getSettings().setJavaScriptEnabled(true);
//        webview_p.loadUrl(" https://selfpause.com/privacy-policy/");
    }
}
