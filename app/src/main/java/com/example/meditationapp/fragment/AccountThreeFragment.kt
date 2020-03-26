package com.app.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import kotlinx.android.synthetic.main.account_three_fragment.*


class AccountThreeFragment : Fragment()
{

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.account_three_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        txt_upgrade.setOnClickListener(View.OnClickListener {
           // val intent = Intent(context, PromoActivity::class.java)
            // val intent = Intent(this, VideocallingActivity::class.java)
           // startActivity(intent)
           // activity!!.finish()

            val weightFragment = PromoFragment.newInstance()
            openFragment(weightFragment)


        })
        ll_setting.setOnClickListener(View.OnClickListener {
//            val weightFragment = AccountFragment.newInstance()
//            openFragment(weightFragment)
             val intent = Intent(context, com.example.meditationapp.javaActivities.SettingActivity::class.java)
             startActivity(intent)
        })
    }

    companion object {
        fun newInstance(): AccountThreeFragment = AccountThreeFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
