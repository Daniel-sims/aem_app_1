package com.management.engineering.alarm.alarmengineermanagement.data.modules

import com.management.engineering.alarm.alarmengineermanagement.features.clients.ClientsViewModel
import com.management.engineering.alarm.alarmengineermanagement.features.home.HomeViewModel
import com.management.engineering.alarm.alarmengineermanagement.features.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel { LoginViewModel(get()) }

    viewModel { HomeViewModel(get()) }

    viewModel { ClientsViewModel() }
}