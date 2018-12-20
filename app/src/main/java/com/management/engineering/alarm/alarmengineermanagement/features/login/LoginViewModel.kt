package com.management.engineering.alarm.alarmengineermanagement.features.login

import androidx.lifecycle.ViewModel
import com.management.engineering.alarm.alarmengineermanagement.data.repositories.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getString() : String {
        return userRepository.getString()
    }
}