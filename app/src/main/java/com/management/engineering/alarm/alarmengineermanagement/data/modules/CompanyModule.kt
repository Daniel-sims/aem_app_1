package com.management.engineering.alarm.alarmengineermanagement.data.modules

import com.management.engineering.alarm.alarmengineermanagement.data.repositories.CompanyRepository
import org.koin.dsl.module.module

val companyModule = module {

    factory { CompanyRepository(get()) }

}