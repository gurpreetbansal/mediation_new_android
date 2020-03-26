package com.example.meditationapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import kotlinx.android.synthetic.main.my_recording_fragment.*
import kotlinx.android.synthetic.main.tool_bar_three.*


class MyRecordingFragment : Fragment()
{
    val group: ArrayList<String> = ArrayList()
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.my_recording_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        ll_weight_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = WeightFragment.newInstance()
            openFragment(weightFragment)
        })
        ll_professional_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = ProfessionalFragment.newInstance()
            openFragment(weightFragment)
        })
        ll_stress_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = StressFragment.newInstance()
            openFragment(weightFragment)
        })
        ll_relationship_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = RelationshipFragment.newInstance()
            openFragment(weightFragment)
        })
        ll_health_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = HealthFragment.newInstance()
            openFragment(weightFragment)
        })
        ll_athletic_recording.setOnClickListener(View.OnClickListener {
            val weightFragment = AthleticFragment.newInstance()
            openFragment(weightFragment)
        })
//        img_tool_bar_three_back.setOnClickListener(View.OnClickListener {
//            val weightFragment = AthleticFragment.newInstance()
//            openFragment(weightFragment)
//        })
        img_tool_bar_three_back.setOnClickListener(View.OnClickListener {
            // getActivity()!!.finish();
            if (getFragmentManager()!!.getBackStackEntryCount() != 0) {
                getFragmentManager()!!.popBackStack();
            }

          //  val weightFragment = AthleticFragment.newInstance()
          //  openFragment(weightFragment)
           // Toast.makeText(context,"CLICKED",Toast.LENGTH_LONG).show()
        })

    }

    companion object {
        fun newInstance(): MyRecordingFragment = MyRecordingFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
