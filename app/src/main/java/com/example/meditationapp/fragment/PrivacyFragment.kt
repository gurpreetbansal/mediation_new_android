package com.app.myapplication.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import kotlinx.android.synthetic.main.privacy_fragment.*


class PrivacyFragment : Fragment()
{

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.privacy_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        img_privacy_back.setOnClickListener(View.OnClickListener {
            val weightFragment = AccountFragment.newInstance()
            openFragment(weightFragment)
        })
//        txt_sub.setOnClickListener(View.OnClickListener {
//            val weightFragment =AccountThreeFragment .newInstance()
//            openFragment(weightFragment)
//        })
//        txt_notification.setOnClickListener(View.OnClickListener {
//            val weightFragment = NotificationFragment.newInstance()
//            openFragment(weightFragment)
//        })
    }

    companion object {
        fun newInstance(): PrivacyFragment = PrivacyFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
