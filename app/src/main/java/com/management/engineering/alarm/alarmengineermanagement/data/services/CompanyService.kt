package com.management.engineering.alarm.alarmengineermanagement.data.services

import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import retrofit2.Call
import retrofit2.http.GET

interface CompanyService {

    companion object {
        private const val GET_COMPANY = "company/"
    }

    @GET(GET_COMPANY)
    fun getCompany(): Call<CompanyResponse>
}