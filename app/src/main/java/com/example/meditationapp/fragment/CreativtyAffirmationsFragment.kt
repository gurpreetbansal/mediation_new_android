package com.example.meditationapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.CongratulationActivity
import com.example.meditationapp.activities.LogOutActivity
import kotlinx.android.synthetic.main.tool_bar_four.*


class CreativtyAffirmationsFragment : Fragment()
{
    val group: ArrayList<String> = ArrayList()
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */

    private val SPLASH_TIME_OUT = 5000
    private var handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.creativity_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        img_heart.visibility=View.VISIBLE
//        img_back_four.setOnClickListener(View.OnClickListener {
//            // getActivity()!!.finish();
//            if (getFragmentManager()!!.getBackStackEntryCount() != 0) {
//                getFragmentManager()!!.popBackStack();
//            }
//        })


        img_back_four.setOnClickListener(View.OnClickListener {
            // getActivity()!!.finish();
            val voice = Intent(context, LogOutActivity::class.java)
            startActivity(voice)
        })

        handler.postDelayed(
            {
                val intent = Intent(context, CongratulationActivity::class.java)
                // val intent = Intent(this, VideocallingActivity::class.java)
                startActivity(intent)
                activity!!.finish()

                //  }

            }, SPLASH_TIME_OUT.toLong()
        )

    }

    companion object {
        fun newInstance(): CreativtyAffirmationsFragment = CreativtyAffirmationsFragment()
    }

}
