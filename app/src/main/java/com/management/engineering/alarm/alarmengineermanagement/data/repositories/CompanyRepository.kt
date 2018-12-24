package com.management.engineering.alarm.alarmengineermanagement.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.data.services.CompanyService
import com.management.engineering.alarm.alarmengineermanagement.data.services.RetrofitClient
import com.management.engineering.alarm.alarmengineermanagement.utils.AppException
import com.management.engineering.alarm.alarmengineermanagement.utils.BASE_URL
import com.management.engineering.alarm.alarmengineermanagement.utils.PreferencesHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyRepository(val context: Context) {

    fun getCompany(): LiveData<Resource<CompanyResponse>> {
        val company = MutableLiveData<Resource<CompanyResponse>>()

        RetrofitClient.getClient(BASE_URL, PreferencesHelper(context.applicationContext).token)!!.create(CompanyService::class.java).getCompany(PreferencesHelper(context.applicationContext).companyId).enqueue(object : Callback<CompanyResponse> {
            override fun onResponse(call: Call<CompanyResponse>?, response: Response<CompanyResponse>?) {
                if (response?.isSuccessful != null && response.isSuccessful) {
                    company.value = Resource.success(response.body())
                }
            }

            override fun onFailure(call: Call<CompanyResponse>?, t: Throwable?) {
                company.value = Resource.error(AppException(t))
            }
        })

        return company
    }
}