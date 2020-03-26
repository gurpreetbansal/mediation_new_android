package com.example.meditationapp.adapter

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meditationapp.R
import com.zerobranch.layout.SwipeLayout
import kotlinx.android.synthetic.main.adapter_notifications.view.*
import kotlinx.android.synthetic.main.custom_dialog_notification_delete.view.*

class NotificationAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolderNotification>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderNotification
    {

        return ViewHolderNotification(LayoutInflater.from(context).inflate(R.layout.adapter_notifications, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolderNotification, p1: Int)
    {


        //   ed_change_password.text = "tzf;fk;"   // Welcome in Tamil
        // Set the typeface


      //  p0?.txt_noti_des.typeface = typeface
        p0?.txt_date?.text = items.get(p1)

        if ( p0?.right_view != null)
        {
            p0?.right_view.setOnClickListener(View.OnClickListener {

            })
        }
        p0?.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener,
            mobile.sarproj.com.layout.SwipeLayout.SwipeActionsListener {
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if (direction == SwipeLayout.LEFT && isContinuous) {

                }
            }

            override fun onClose() {

            }
        })

        p0?.right_view.setOnClickListener{
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_notification_delete, null)




            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
            //   .setTitle("Login Form")
            //show dialog
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.txt_delete.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
               /* val name = mDialogView.ed_start_date.text.toString()
                val email = mDialogView.ed_end_date.text.toString()*/

                //set the input text in TextView
                // mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
            }
            //cancel button click of custom layout
            mDialogView.txt_cancel.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }



    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
    private fun remove(context: Context, position: Int) {
        Toast.makeText(context, "removed item $position", Toast.LENGTH_SHORT).show()
    }
}

class ViewHolderNotification (view: View) : RecyclerView.ViewHolder(view)
{
    // Holds the TextView that will add each animal to
    val txt_date = view.txt_date
    //val txt_noti_des = view.txt_noti_des
    val right_view = view.right_view
    val swipeLayout = view.swipe_layout
    val drag_item1 = view.drag_item1






}