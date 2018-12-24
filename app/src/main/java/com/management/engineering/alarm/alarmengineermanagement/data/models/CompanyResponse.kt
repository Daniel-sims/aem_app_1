package com.management.engineering.alarm.alarmengineermanagement.data.models

import com.google.gson.annotations.SerializedName

data class CompanyResponse(

        @SerializedName("name")
        val name: String,

        @SerializedName("modules")
        val modules: List<CompanyModuleResponse>

)