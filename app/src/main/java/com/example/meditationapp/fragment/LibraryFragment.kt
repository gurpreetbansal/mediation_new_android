package com.app.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.*
import kotlinx.android.synthetic.main.fragment_lib.*


class LibraryFragment : Fragment()
{
    val group: ArrayList<String> = ArrayList()
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.fragment_lib, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
      //txt_home_my_recording"
        //txt_home_my_favourite
        ll_weight_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, WeighTwoActivity::class.java)
            startActivity(voice)
        })
        ll_weight_lib_two.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, WeighTwoActivity::class.java)
            startActivity(voice)
        })
//        ll_home_search.setOnClickListener(View.OnClickListener {
//            val weightFragment = SearchFragment.newInstance()
//            openFragment(weightFragment)
//        })
        txt_home_my_recording.setOnClickListener(View.OnClickListener {
          //  val weightFragment = MyRecordingFragment.newInstance()
          //  openFragment(weightFragment)
            val voice = Intent(activity, My_RecordingActivity::class.java)
            startActivity(voice)
        })
        txt_home_my_favourite.setOnClickListener(View.OnClickListener {
//            val weightFragment = MyFavoritesFragment.newInstance()
//            openFragment(weightFragment)
            val voice = Intent(activity, My_FavoritesActivity::class.java)
            startActivity(voice)
        })
        ll_weight_loss_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, WeighTwoActivity::class.java)
            startActivity(voice)
        })
        ll_weight_loss2_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, WeighTwoActivity::class.java)
            startActivity(voice)
        })
        llprofessional_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, ProfessionalTwoActivity::class.java)
            startActivity(voice)
        })
        ll_professional2_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, ProfessionalTwoActivity::class.java)
            startActivity(voice)
        })
        ll_stress_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, StressTwoActivity::class.java)
            startActivity(voice)
        })
        ll_stress2_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, StressTwoActivity::class.java)
            startActivity(voice)
        })
        ll_relation_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, RelationshipTwoActivity::class.java)
            startActivity(voice)
        })
        ll_athletic_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, AthleicTwoActivity::class.java)
            startActivity(voice)
        })
        ll_healths_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, HealthTwoActivity::class.java)
            startActivity(voice)
        })
        ll_fincial_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, FinancialTwoActivity::class.java)
            startActivity(voice)
        })
        ll_abun_lib.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, AbundanceTwoActivity::class.java)
            startActivity(voice)
        })

        img_lib_drop.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })
        img_lib_stone.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })
        img_lib_tree.setOnClickListener(View.OnClickListener {
            val voice = Intent(activity, CreativtyAffirmationsActivity::class.java)
            startActivity(voice)
        })
        img_lib_water.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
        img_lib_flower.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })

        img_lib_beat.setOnClickListener(View.OnClickListener {
            val voice = Intent(context, GetMore_Activity::class.java)
            startActivity(voice)
        })
    }

    companion object {
        fun newInstance(): LibraryFragment = LibraryFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
