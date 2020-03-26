package com.example.meditationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.tool_bar_two.*

class WeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_fragment)
        img_back_two.setOnClickListener(View.OnClickListener {
           finish()
        })
    }
}
