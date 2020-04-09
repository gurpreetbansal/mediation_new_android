package com.app.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.*
import com.example.meditationapp.javaActivities.SupportActivity_new
import kotlinx.android.synthetic.main.account_fragment.*


class AccountFragment : Fragment()
{

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.account_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        txt_account.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, AccountSettingActivity::class.java)
            startActivity(intent)
        })

        img_account_first_back.setOnClickListener(View.OnClickListener {
            val weightFragment = LibraryFragment.newInstance()
            openFragment(weightFragment)
        })



        txt_sub.setOnClickListener(View.OnClickListener {
            val weightFragment =AccountThreeFragment .newInstance()
            openFragment(weightFragment)
        })
        txt_notification.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
        })
        txt_terms_condition.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, Terms_And_Conditions_Acxtivity::class.java)
            startActivity(intent)
        })
        txt_support.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, SupportActivity_new::class.java)
            startActivity(intent)
        })
        txt_privacy.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, PrivacyActivity::class.java)
            startActivity(intent)
        })
//        txt_sub.setOnClickListener(View.OnClickListener {
//            val intent = Intent(context, PromoActivity::class.java)
//            // val intent = Intent(this, VideocallingActivity::class.java)
//            startActivity(intent)
//            activity!!.finish()
//
//        })
        txt_logout.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, LogOut_Activity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
            startActivity(intent)
           // activity!!.finish()

        })


        txt_help_center.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, HelpCenter_Activity::class.java)
            startActivity(intent)

        })
    }

    companion object {
        fun newInstance(): AccountFragment = AccountFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
