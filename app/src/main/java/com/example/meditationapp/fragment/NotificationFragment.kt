//package com.t.meditationapp.fragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.app.myapplication.fragment.AccountFragment
//
//import com.t.meditationapp.R
//import com.t.meditationapp.adapter.NotificationAdapter
//import kotlinx.android.synthetic.main.nitification_fragment.*
//
//
//class NotificationFragment : Fragment()
//{
//    val group: ArrayList<String> = ArrayList()
//    val notification: ArrayList<String> = ArrayList()
//    /* override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
//         inflater.inflate(R.layout.fragment_cart, container, false)
// */
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        //   var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
//        var rootView = inflater!!.inflate(R.layout.nitification_fragment, container, false)
//        return rootView
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
//    {
//        super.onViewCreated(view, savedInstanceState)
//
//
////        ll_abun_first.setOnClickListener(View.OnClickListener {
////            val weightFragment = AbundanceTwoFragment.newInstance()
////            openFragment(weightFragment)
////        })
//
//        img_notification_back.setOnClickListener(View.OnClickListener {
//                        val weightFragment = AccountFragment.newInstance()
//            openFragment(weightFragment)
//        })
//        recyclerview_notification.layoutManager =
//            LinearLayoutManager(activity)
//        // Access the RecyclerView Adapter and load the data into it
//        recyclerview_notification.adapter = NotificationAdapter(notification, activity!!)
//        addlist()
//    }
//
//    companion object {
//        fun newInstance(): NotificationFragment = NotificationFragment()
//    }
//    private fun openFragment(fragment: Fragment) {
//        val transaction =activity?.supportFragmentManager?.beginTransaction()
//        transaction?.replace(R.id.container, fragment)
//        transaction?.addToBackStack(null)
//        transaction?.commit()
//    }
//    fun addlist() {
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//        notification.add("June 2,2019, 07:00")
//
//    }
//}
