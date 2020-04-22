package com.app.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.CreativtyAffirmationsActivity
import com.example.meditationapp.activities.GetMore_Activity
import kotlinx.android.synthetic.main.sound_fragment.*
import kotlinx.android.synthetic.main.tool_bar.*


class SoundFragment : Fragment()
{

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.sound_fragment, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
//        fl_drop.setOnClickListener(View.OnClickListener {
//            val voice = Intent(activity, CreativtyAffirmationsActivity::class.java)
//            startActivity(voice)
//        })
//        fl_stone_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_tree_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_water_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_flower_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_beat_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_mouth_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_flute_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        fl_beat_two_sound.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, GetMore_Activity::class.java)
//            startActivity(voice)
//        })
//        img_back_tool.visibility=View.INVISIBLE
    }

    companion object {
        fun newInstance(): SoundFragment = SoundFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
