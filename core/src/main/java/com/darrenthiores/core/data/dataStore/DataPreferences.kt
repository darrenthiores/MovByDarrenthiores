package com.darrenthiores.core.data.dataStore

import android.content.Context
import androidx.core.content.edit

class DataPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getUsername():String = preferences.getString(USERNAME_KEY, "") ?: ""

    fun setUser(username:String){
        preferences.edit {
            putString(USERNAME_KEY, username)
        }
    }

    fun getLogin():Boolean = preferences.getBoolean(IS_LOGIN_KEY, false)

    fun setLogin(isLogin:Boolean){
        preferences.edit {
            putBoolean(IS_LOGIN_KEY, isLogin)
        }
    }

    companion object{
        private const val USERNAME_KEY = "USERNAME_KEY"
        private const val IS_LOGIN_KEY = "IS_LOGIN_KEY"
        private const val PREF_NAME = "DATA_PREF"
    }

}