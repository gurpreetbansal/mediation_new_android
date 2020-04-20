package com.example.meditationapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import com.example.meditationapp.adapter.Voice_Selector_Adapter
import com.example.meditationapp.javaActivities.VoiceSelect_Activity
import kotlinx.android.synthetic.main.creativity_two_fragment.*
import kotlinx.android.synthetic.main.tool_bar_four.*

class CreativtyAffirmationsActivity : AppCompatActivity() {
    val group: ArrayList<String> = ArrayList()
    var list_of_items = arrayOf("Alice", "Tiffany", "Kevin")
    val listItemsTxt = arrayOf("Marvin","Alice", "Tiffany", "Kevin")
    private val SPLASH_TIME_OUT = 1000
    private var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creativity_two_fragment)
        var spinnerAdapter: Voice_Selector_Adapter = Voice_Selector_Adapter(this@CreativtyAffirmationsActivity, listItemsTxt)
        var spinner: Spinner = findViewById(R.id.spinner_voice) as Spinner
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
                            val voice = Intent(this@CreativtyAffirmationsActivity, VoiceSelect_Activity::class.java)
                            startActivity(voice)

                            //  }

                        }, SPLASH_TIME_OUT.toLong())
                }

            }
        })



        img_music.setOnClickListener(View.OnClickListener {
            // val weightFragment = CreativtyAffirmationsFragment.newInstance()
            // openFragment(weightFragment)

            if(ll_options.getVisibility() == View.VISIBLE)
            {
                ll_options.visibility= View.INVISIBLE
                // img_vol_bar.visibility=View.INVISIBLE
            }
            else{
                ll_options.setVisibility(View.VISIBLE);
                // img_vol_bar.visibility=View.VISIBLE
            }

        })

        img_one.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })

        img_two.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })
        img_three.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })
        img_four.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })
        img_five.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })
        img_six.setOnClickListener(View.OnClickListener {
            img_vol_bar.visibility= View.VISIBLE
        })

        img_heart.visibility= View.VISIBLE
//        img_back_four.setOnClickListener(View.OnClickListener {
//           // getActivity()!!.finish();
//            if (getFragmentManager()!!.getBackStackEntryCount() != 0) {
//                getFragmentManager()!!.popBackStack();
//            }
//        })
        img_heart.setOnClickListener(View.OnClickListener {
//            val weightFragment = MyFavoritesFragment.newInstance()
//            openFragment(weightFragment)
            val voice = Intent(this@CreativtyAffirmationsActivity, My_FavoritesActivity::class.java)
            startActivity(voice)

        })

        img_back_four.setOnClickListener(View.OnClickListener {
            // getActivity()!!.finish();
//            val voice = Intent(this@CreativtyAffirmationsActivity, LogOutActivity::class.java)
//            startActivity(voice)
            finish()
        })


    }
    private fun openFragment(fragment: Fragment) {
        val transaction =supportFragmentManager?.beginTransaction()
        transaction?.replace(com.example.meditationapp.R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
