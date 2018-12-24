package com.management.engineering.alarm.alarmengineermanagement.data.services

import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CompanyService {

    companion object {
        private const val GET_COMPANY = "company/{pk}/"
        private const val GET_CLIENTS = "clients/"
    }

    @GET(GET_COMPANY)
    fun getCompany(@Path("pk") pk: Int): Call<CompanyResponse>

    @GET(GET_CLIENTS)
    fun getClients(): Call<List<ClientResponse>>
}