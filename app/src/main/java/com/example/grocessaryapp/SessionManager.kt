package com.example.grocessaryapp

import android.content.Context
import com.example.grocessaryapp.models.User

class SessionManager (var mContext: Context) {

    private val FILE_NAME = "my_file"
    private val KEY_EMAIL = "email"
    private val KEY_FIRST_NAME = "fname"
    private val KEY_PASSWORD = "password"
    private val KEY_TOKEN = "token"
    private val KEY_IS_LOGGED_IN = "logged_in"

    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun saveUser(user: User?, token:String?) {
        editor.putString(KEY_EMAIL, user?.email)
        editor.putString(KEY_PASSWORD, user?.password)
        editor.putString(KEY_FIRST_NAME, user?.firstName)
        editor.putString(KEY_TOKEN, token)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }

    fun isLoggedIn():Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getName(): String?{
        return  sharedPreferences.getString(KEY_FIRST_NAME, null)
    }

    fun getToken():String?{
        return sharedPreferences.getString(KEY_TOKEN,null)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }


}