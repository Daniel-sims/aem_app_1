package com.management.engineering.alarm.alarmengineermanagement.data.modules

import com.management.engineering.alarm.alarmengineermanagement.data.services.UserService
import org.koin.dsl.module.module

val networkModule = module {
    single { UserService }


}