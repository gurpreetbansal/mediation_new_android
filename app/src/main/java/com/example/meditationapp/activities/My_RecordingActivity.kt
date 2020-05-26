package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapp.R
import kotlinx.android.synthetic.main.my_recording_fragment.*
import kotlinx.android.synthetic.main.tool_bar_three.*

class My_RecordingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_recording_fragment)
        img_tool_bar_three_back.setOnClickListener(View.OnClickListener {
           finish()
        })
//        ll_weight_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, WeightActivity::class.java)
//            startActivity(voice)
//        })
//        ll_professional_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, ProfessionalActivity::class.java)
//            startActivity(voice)
//        })
//        ll_stress_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, StressActivity::class.java)
//            startActivity(voice)
//        })
//        ll_relationship_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, RelationshipActivity::class.java)
//            startActivity(voice)
//        })
//        ll_athletic_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, AthleticActivity::class.java)
//            startActivity(voice)
//        })
//        ll_health_recording.setOnClickListener(View.OnClickListener {
//            val voice = Intent(this@My_RecordingActivity, HealthActivity::class.java)
//            startActivity(voice)
//        })
    }
}
