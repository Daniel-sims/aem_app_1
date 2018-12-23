package com.management.engineering.alarm.alarmengineermanagement.data.modules

import com.management.engineering.alarm.alarmengineermanagement.data.repositories.UserRepository
import com.management.engineering.alarm.alarmengineermanagement.utils.PreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val userModule = module {

    single { UserRepository() }

    single { PreferencesHelper(androidContext()) }
}