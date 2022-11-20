package com.example.newstore.utils

import android.content.Context
import android.content.SharedPreferences

object MUtils {
    fun saveToShared(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences("SHP", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun readToShared(context: Context, key: String): String? {
        val sharedPreferences = context.getSharedPreferences("SHP", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun saveStateLogin(context: Context,key:String,value:Boolean){
        val sharedPreferences = context.getSharedPreferences("SHP", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key,value)
        editor.apply()
    }

    fun checkLogin(context: Context, key: String): Boolean? {
        val sharedPreferences = context.getSharedPreferences("SHP", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

}