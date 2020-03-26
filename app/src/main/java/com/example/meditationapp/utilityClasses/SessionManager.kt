package com.imarkinfotech.slowme.utilityClasses


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.meditationapp.activities.LoginActivity

class SessionManager// Constructor
    (// Context
    internal var _context: Context
) {

    // Shared Preferences
    internal var pref: SharedPreferences

    // Editor for Shared preferences
    internal var editor: SharedPreferences.Editor

    // Shared pref mode
    internal var PRIVATE_MODE = 0

    /**
     * Get stored session data
     */
    // user name
    // user email id
    // return user
    /* val userDetails: HashMap<String, String>
         get() {
             val user = HashMap<String, String>()
             user[KEY_NAME] = pref.getString(KEY_NAME, null)
             user[KEY_EMAIL] = pref.getString(KEY_EMAIL, null)

             user[KEY_ID] = pref.getString(KEY_ID, null)
             user[PASSWORD] = pref.getString(PASSWORD, null)
             return user
         }*/

    /**
     * Quick check for login
     */
    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
        editor.apply()
    }


    fun HostessProfilePicpref(profileImg: String) {

        editor.putString(KEY_PROFILEIMG, profileImg)

        // commit changes
        editor.commit()
    }


    fun HostessOnlineStatusspref(offlineoOnline: Boolean) {

        editor.putBoolean(IS_ONLINE, offlineoOnline)

        // commit changes
        editor.commit()
    }

    fun settingsprefnewsalert(Isnewsalert: Boolean) {

        editor.putBoolean(IS_NEWSALERT, Isnewsalert)

        // commit changes
        editor.commit()
    }

    fun settingsprefupdates(Isupdates: Boolean) {

        editor.putBoolean(IS_UPDATES, Isupdates)
        // commit changes
        editor.commit()
    }

    /**
     * Create login session
     */
    fun createSession(


        name: String,
        email: String,
        device_token: String,
        device_type: String,
        profileImg: String,
        id: String,
        status: String,
        auth_token: String,
        password: String,
        phone: String,
        notification: Int,
        login_by:String,
        address1:String,
        address2:String,
        location:String,
        zip:String



        //  update_at:Int
    )
    {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(PASSWORD, password)
        // Storing name in pref
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_PROFILEIMG, profileImg)
        // Storing email in pref
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_ID, id)
        editor.putString(KEY_STATUS, status)
        editor.putString(KEY_DEVICE_TOKEN, device_token)
        editor.putString(KEY_TOKEN, auth_token)
        editor.putString(KEY_DEVICE_TYPE, device_type)
        editor.putString(KEY_PHONE, phone)

        editor.putString(KEY_ADDRESS1, address1)
        editor.putString(KEY_ADDRESS2, address2)
        editor.putString(KEY_LOCATION, location)
        editor.putString(KEY_ZIP, zip)

        editor.putInt(KEY_NOTIFICATION, notification)

        // commit changes
        editor.commit()
    }



    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.commit()


        // After logout redirect user to Loing Activity
        val i = Intent(_context, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        // Staring Login Activity
        _context.startActivity(i)

    }

    companion object {

        private val PREF_NAME = "LOGIN_INFO"

        // All Shared Preferences Keys
        private val IS_ONLINE = "offlineoOnline"
        private val IS_NEWSALERT = "Isnewsalert"
        private val IS_UPDATES = "Isupdates"
        private val IS_LOGIN = "IsLoggedIn"
        private val PASSWORD = "Password"
        // User name (make variable public to access from outscreditse)
        val KEY_NAME = "NAME"
        // Email address (make variable public to access from outscreditse)
        val KEY_EMAIL = "EMAIL"
        val KEY_ID = "id"
        val KEY_STATUS = "status"
        val RATING = "RATING"
        private val KEY_PROFILEIMG = "profileImg"
        private val SESSION_iD = "session_credits"
        /*new*/
        private val KEY_DEVICE_TOKEN = "device_token"
        private val KEY_TOKEN = "token"
        private val KEY_DEVICE_TYPE = "device_type"
        private val KEY_PHONE = "phone"
        private val KEY_NOTIFICATION = "notification"
        private val KEY_UPDATE_AT = "updated_at"
        private val KEY_LOGING_BY = "login_by"

        private val KEY_ADDRESS1 = "address1"
        private val KEY_ADDRESS2 = "address2"
        private val KEY_LOCATION = "address3"
        private val KEY_ZIP = "zip"

    }








}
