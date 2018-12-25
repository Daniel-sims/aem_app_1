package com.management.engineering.alarm.alarmengineermanagement.features.clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.management.engineering.alarm.alarmengineermanagement.data.models.ClientResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.data.repositories.CompanyRepository

class ClientsViewModel(private val companyRepository: CompanyRepository) : ViewModel() {

    fun getClients(): LiveData<Resource<List<ClientResponse>>> {
        return companyRepository.getClients()
    }

}