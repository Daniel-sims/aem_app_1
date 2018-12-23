package com.management.engineering.alarm.alarmengineermanagement.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.management.engineering.alarm.alarmengineermanagement.data.models.CompanyResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.data.repositories.CompanyRepository

class HomeViewModel(private val companyRepository: CompanyRepository) : ViewModel() {

    fun getCompany(): LiveData<Resource<CompanyResponse>> {
        return companyRepository.getCompany()
    }

}