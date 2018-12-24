package com.management.engineering.alarm.alarmengineermanagement.data.models

import com.google.gson.annotations.SerializedName

data class LoginPostData(

        @SerializedName("username")
        val username: String,

        @SerializedName("password")
        val password: String

)