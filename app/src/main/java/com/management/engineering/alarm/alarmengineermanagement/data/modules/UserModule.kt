package com.management.engineering.alarm.alarmengineermanagement.data.modules

import com.management.engineering.alarm.alarmengineermanagement.data.repositories.UserRepository
import org.koin.dsl.module.module

val userModule = module {

    single { UserRepository() }
}