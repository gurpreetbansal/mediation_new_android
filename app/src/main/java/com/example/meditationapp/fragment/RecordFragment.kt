package com.app.myapplication.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.meditationapp.R
import com.example.meditationapp.activities.*
import com.example.meditationapp.javaActivities.CreativityAffirmationActivityNew
import kotlinx.android.synthetic.main.my_recording_fragment.*
import kotlinx.android.synthetic.main.tool_bar_three.*


class RecordFragment : Fragment() {

    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_cart, container, false)
 */
    val am: ArrayList<String> = ArrayList()
    private var button1IsVisible = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater!!.inflate(R.layout.my_recording_fragment, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recording_playall.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, CreativityAffirmationActivityNew::class.java)
//            intent.putExtra("demo", "https://clientstagingdev.com/meditation/public/voice/1586425636.mp3")
            startActivity(intent)
        })

//        ll_weight_recording.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, WeightActivity::class.java)
//            startActivity(intent)
//        })
//        ll_professional_recording.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, ProfessionalActivity::class.java)
//            startActivity(intent)
//        })
//        ll_stress_recording.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, StressActivity::class.java)
//            startActivity(intent)
//        })
//        ll_relationship_recording.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, RelationshipActivity::class.java)
//            startActivity(intent)
//        })
//        ll_athletic_recording.setOnClickListener(View.OnClickListener {
////            val weightFragment = Ath.newInstance()
////            openFragment(weightFragment)
//            val intent = Intent(activity, AthleticActivity::class.java)
//            startActivity(intent)
//        })
//        ll_health_recording.setOnClickListener(View.OnClickListener {
//            val intent = Intent(activity, HealthActivity::class.java)
//            startActivity(intent)
//        })
        img_tool_bar_three_back.setOnClickListener(View.OnClickListener {
            // getActivity()!!.finish();
            if (getFragmentManager()!!.getBackStackEntryCount() != 0) {
                getFragmentManager()!!.popBackStack();
            }

            // val weightFragment = AthleticFragment.newInstance()
            // openFragment(weightFragment)
            // Toast.makeText(context,"CLICKED",Toast.LENGTH_LONG).show()
        })
        // img_tool_bar_three_back
    }

    companion object {
        fun newInstance(): RecordFragment = RecordFragment()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
