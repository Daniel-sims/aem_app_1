package com.management.engineering.alarm.alarmengineermanagement.utils

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {
    companion object {
        private const val TOKEN = "data.source.prefs.TOKEN"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    // val token = PreferencesHelper(context!!.applicationContext).token
    // save token
    var token = preferences.getString(TOKEN, "")!!
    set(value) = preferences.edit().putString(TOKEN, value).apply()
}