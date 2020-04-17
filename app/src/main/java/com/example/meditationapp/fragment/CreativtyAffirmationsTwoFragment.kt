package com.example.meditationapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment

import com.example.meditationapp.R
import com.example.meditationapp.activities.*
import com.example.meditationapp.adapter.Voice_Selector_Adapter
import kotlinx.android.synthetic.main.creativity_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_four.*





class CreativtyAffirmationsTwoFragment : Fragment(),AdapterView.OnItemSelectedListener
{
    val group: ArrayList<String> = ArrayList()
    var list_of_items = arrayOf("Alice", "Tiffany", "Kevin")
    val listItemsTxt = arrayOf("Marvin","Alice", "Tiffany", "Kevin")
    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    private val SPLASH_TIME_OUT = 1000
    private var handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        var rootView = inflater!!.inflate(R.layout.creativity_two_fragment, container, false)
        return rootView
    }
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
//        spinner_voice!!.setOnItemSelectedListener(this)
//
//        // Create an ArrayAdapter using a simple spinner layout and languages array
//        val aa = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, list_of_items)
//        // Set layout to use when the list of choices appear
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        // Set Adapter to Spinner
//        spinner_voice!!.setAdapter(aa)

        var spinnerAdapter: Voice_Selector_Adapter = Voice_Selector_Adapter(context!!, listItemsTxt)
        var spinner: Spinner = view.findViewById(R.id.spinner_voice) as Spinner
        spinner?.adapter = spinnerAdapter
        spinner?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

if(position==0)
{

}
                else {
            handler.postDelayed(
            {
                val voice = Intent(context, VoiceSelect_Activity::class.java)
                startActivity(voice)

                //  }

            }, SPLASH_TIME_OUT.toLong())




                }

            } // to close the onItemSelected



    })



        img_music.setOnClickListener(View.OnClickListener {
           // val weightFragment = CreativtyAffirmationsFragment.newInstance()
           // openFragment(weightFragment)

            if(ll_options.getVisibility() == View.VISIBLE)
            {
                ll_options.visibility=View.INVISIBLE
               // img_vol_bar.visibility=View.INVISIBLE
            }
            else{
                ll_options.setVisibility(View.VISIBLE);
               // img_vol_bar.visibility=View.VISIBLE
            }

        })

//        img_one.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })
//
//        img_two.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })
//        img_three.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })
//        img_four.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })
//        img_five.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })
//        img_six.setOnClickListener(View.OnClickListener {
//            img_vol_bar.visibility=View.VISIBLE
//        })

        img_heart.visibility=View.VISIBLE
//        img_back_four.setOnClickListener(View.OnClickListener {
//           // getActivity()!!.finish();
//            if (getFragmentManager()!!.getBackStackEntryCount() != 0) {
//                getFragmentManager()!!.popBackStack();
//            }
//        })

        img_heart.setOnClickListener(View.OnClickListener {
             val weightFragment = MyFavoritesFragment.newInstance()
             openFragment(weightFragment)

        })

        img_back_four.setOnClickListener(View.OnClickListener {
                       // getActivity()!!.finish();
            val voice = Intent(context, LogOutActivity::class.java)
            startActivity(voice)
        })


//        spinner_voice.setOnClickListener(View.OnClickListener {
//            val voice = Intent(context, VoiceSelect_Activity::class.java)
//            startActivity(voice)
//        })
//        handler.postDelayed(
//            {
//                val intent = Intent(context, CongratulationActivity::class.java)
//                // val intent = Intent(this, VideocallingActivity::class.java)
//                startActivity(intent)
//                activity!!.finish()
//
//                //  }
//
//            }, SPLASH_TIME_OUT.toLong()
//        )
    }



    companion object {
        fun newInstance(): CreativtyAffirmationsTwoFragment = CreativtyAffirmationsTwoFragment()
    }
    private fun openFragment(fragment: Fragment) {
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(com.example.meditationapp.R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}
