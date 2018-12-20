package com.management.engineering.alarm.alarmengineermanagement.data.services

import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostData
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    companion object {
        private const val POST_USER_LOGIN = "users/login/"
    }

    @POST(POST_USER_LOGIN)
    fun postLogin(@Body data: LoginPostData): Call<LoginPostResponse>
}