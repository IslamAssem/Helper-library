package com.islamassem.utils

import android.content.Context
import android.text.TextUtils
import androidx.preference.PreferenceManager
import com.google.gson.Gson

@Suppress("MemberVisibilityCanBePrivate", "unused")
object SharedPreferencesHelper {
    var mContext: Context? = null
    fun saveSharedPreferences(key: String, value: Boolean, context: Context? = mContext) {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getSharedPreferences(
        key: String,
        defValue: Boolean,
        context: Context? = mContext
    ): Boolean {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context)
        return editor.getBoolean(key, defValue)
    }

    fun saveSharedPreferences(key: String, value: Int, context: Context? = mContext) {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getSharedPreferences(key: String, defValue: Int, context: Context? = mContext): Int {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context)
        return editor.getInt(key, defValue)
    }

    fun saveSharedPreferences(key: String, value: String, context: Context? = mContext) {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getSharedPreferences(key: String, defValue: String, context: Context? = mContext): String {
        //save to value with the key in SharedPreferences
        val editor = PreferenceManager.getDefaultSharedPreferences(context)
        val value = editor.getString(key, defValue)
        return value?:defValue
    }

    fun saveSharedPreferences(key: String, items: Set<String>, context: Context? = mContext) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        editor.putStringSet(key, items)
        editor.apply()
    }

    fun getSharedPreferences(key: String, context: Context? = mContext): Set<String>? {
        val editor = PreferenceManager.getDefaultSharedPreferences(context)
        return editor.getStringSet(key, null)
    }

    fun <T> saveSharedPreferences(
        keepSort: Boolean = true,
        key: String,
        items: List<T>,
        context: Context? = mContext
    ) {
        if (keepSort) {
            val gson = Gson()
            val itemsStrings = ArrayList<String>()
            for (t in items)
                itemsStrings.add(gson.toJson(t))
            saveSharedPreferences(key, TextUtils.join("|", itemsStrings), context)
        } else {
            val gson = Gson()
            val itemsSet = HashSet<String>()
            for (t in items)
                itemsSet.add(gson.toJson(t))
            saveSharedPreferences(key, itemsSet, context)
        }
    }

    fun <T> getSharedPreferences(
        keepSort: Boolean = true,
        key: String,
        tClass: Class<T>,
        context: Context? = mContext
    ): List<T> {
        val gson = Gson()
        val items = ArrayList<T>()
        if (keepSort) {
            val strings = getSharedPreferences(key,"")
            if (!strings.isNullOrBlank()){
               val itemsJsons = TextUtils.split("|", strings)
                for (json in itemsJsons){
                    items.add(gson.fromJson(json,tClass))
                }
            }
        } else {
            val itemsSet = getSharedPreferences(key, context)
            if (!itemsSet.isNullOrEmpty())
                for (json in itemsSet)
                    items.add(gson.fromJson(json, tClass))
        }
        return items
    }
}