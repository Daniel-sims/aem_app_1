package com.management.engineering.alarm.alarmengineermanagement.features

import android.app.Application
import com.management.engineering.alarm.alarmengineermanagement.data.modules.networkModule
import com.management.engineering.alarm.alarmengineermanagement.data.modules.userModule
import com.management.engineering.alarm.alarmengineermanagement.data.modules.viewModelModule
import org.koin.android.ext.android.startKoin

class AEMApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
                viewModelModule,
                userModule,
                networkModule)
        )
    }

}