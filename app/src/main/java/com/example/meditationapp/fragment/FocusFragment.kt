package com.example.meditationapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.WeighTwoActivity
import kotlinx.android.synthetic.main.focus_fragment.*
import kotlinx.android.synthetic.main.tool_bar_two.*


class FocusFragment : Fragment()
{
    val group: ArrayList<String> = ArrayList()
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.focus_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        ll_focus_first.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, WeighTwoActivity::class.java)
            startActivity(voice)
//            val intent = Intent(context, WeightTwoFragment::class.java)
//
//            startActivity(intent)
        })

        img_back_two.setOnClickListener(View.OnClickListener {
            activity!!.finish()
        })
    }

    companion object {
        fun newInstance(): FocusFragment = FocusFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
