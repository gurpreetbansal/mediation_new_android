package com.app.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.Terms_And_Conditions_Acxtivity
import kotlinx.android.synthetic.main.promo_activity.*


class PromoFragment : Fragment()
{

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.promo_activity, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        img_okay.setOnClickListener(View.OnClickListener
        {
            val weightFragment = AccountFragment.newInstance()
            openFragment(weightFragment)
        }
        )
        txt_terms.setOnClickListener(View.OnClickListener {
            val terms = Intent(context, Terms_And_Conditions_Acxtivity::class.java)
            startActivity(terms)
        })

    }

    companion object {
        fun newInstance(): PromoFragment = PromoFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
