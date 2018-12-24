package com.management.engineering.alarm.alarmengineermanagement.data.models

import com.google.gson.annotations.SerializedName

data class CompanyModuleResponse(

        @SerializedName("name")
        val name: String,

        @SerializedName("slug_field")
        val moduleSlug: String

)