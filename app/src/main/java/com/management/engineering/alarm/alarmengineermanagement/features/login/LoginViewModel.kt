package com.management.engineering.alarm.alarmengineermanagement.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.data.repositories.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun postLogin(username: String, password: String) : LiveData<Resource<LoginPostResponse>> {
        return userRepository.postLogin(username, password)
    }
}