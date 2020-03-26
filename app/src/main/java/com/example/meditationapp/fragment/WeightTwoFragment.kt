package com.example.meditationapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.CreativtyAffirmationsActivity
import com.example.meditationapp.activities.GetMore_Activity
import com.example.meditationapp.activities.HomeActivity
import kotlinx.android.synthetic.main.tool_bar_two.*
import kotlinx.android.synthetic.main.weight_two_fragment.*


class WeightTwoFragment : Fragment()
{
    val group: ArrayList<String> = ArrayList()
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.weight_two_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


//        ll_wl_my_recordings.setOnClickListener(View.OnClickListener {
//            val weightFragment = AffirmationsFragment.newInstance()
//            openFragment(weightFragment)
//        })
//        ll_wl_my_recordings.setOnClickListener(View.OnClickListener {
//            val weightFragment = MyRecordingFragment.newInstance()
//            openFragment(weightFragment)
//        })

        ll_wl_my_recordings.setOnClickListener(View.OnClickListener {
            val weightFragment = WeightFragment.newInstance()
            openFragment(weightFragment)
        })

        img_back_two.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
        })
        img_abun.setOnClickListener(View.OnClickListener {
            val weightFragment = AbundanceTwoFragment.newInstance()
            openFragment(weightFragment)
        })
        img_health.setOnClickListener(View.OnClickListener {
            val weightFragment = HealthTwoFragment.newInstance()
            openFragment(weightFragment)
        })
        img_one_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })

        img_two_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_three_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_four_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_five_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_six_weight.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })






    }

    companion object {
        fun newInstance(): WeightTwoFragment = WeightTwoFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
