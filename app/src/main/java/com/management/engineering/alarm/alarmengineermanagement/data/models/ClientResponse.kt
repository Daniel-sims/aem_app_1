package com.management.engineering.alarm.alarmengineermanagement.data.models

import com.google.gson.annotations.SerializedName

data class ClientResponse(

        @SerializedName("id")
        val pk: Int,

        @SerializedName("account_number")
        val accountNumber: String,

        @SerializedName("mobile_number")
        val mobileNumber: String,

        @SerializedName("landline_number")
        val landlineNumber: String,

        @SerializedName("email")
        val email: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("system_details")
        val systemDetails: String,

        @SerializedName("customer")
        val customers: List<CustomerResponse>

)
