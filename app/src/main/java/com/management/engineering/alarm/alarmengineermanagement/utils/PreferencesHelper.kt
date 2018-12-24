package com.management.engineering.alarm.alarmengineermanagement.utils

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {
    companion object {
        private const val TOKEN = "data.source.prefs.TOKEN"
        private const val COMPANY_ID = "data.source.prefs.COMPANY_ID"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var token = preferences.getString(TOKEN, "")!!
    set(value) = preferences.edit().putString(TOKEN, value).apply()

    var companyId = preferences.getInt(COMPANY_ID, -1)
    set(value) = preferences.edit().putInt(COMPANY_ID, value).apply()
}