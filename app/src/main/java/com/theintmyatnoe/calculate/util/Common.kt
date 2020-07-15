package com.theintmyatnoe.calculate.util

import android.app.Activity

class Common {
    companion object{
        internal val mode=Activity.MODE_PRIVATE
        internal val myPreferences="CalculatePreference"

        fun getDataFromSharedPref(key: String, activity: Activity): String? {
            val mySharedPreference = activity.getSharedPreferences(
                myPreferences,
                mode
            )
            return mySharedPreference.getString(key, "")
        }

        fun putDataToSharefPref(key: String, value: Any, activity: Activity) {
            val mySharedPreference = activity.getSharedPreferences(
                myPreferences,
                mode
            )
            val myEditor = mySharedPreference.edit()
            myEditor.putString(key, value.toString())
            myEditor.commit()
        }
    }
}