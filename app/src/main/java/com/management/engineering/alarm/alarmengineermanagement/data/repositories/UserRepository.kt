package com.management.engineering.alarm.alarmengineermanagement.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostData
import com.management.engineering.alarm.alarmengineermanagement.data.models.LoginPostResponse
import com.management.engineering.alarm.alarmengineermanagement.data.models.Resource
import com.management.engineering.alarm.alarmengineermanagement.data.services.RetrofitClient
import com.management.engineering.alarm.alarmengineermanagement.data.services.UserService
import com.management.engineering.alarm.alarmengineermanagement.utils.AppException
import com.management.engineering.alarm.alarmengineermanagement.utils.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {

    private val apiService: UserService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(UserService::class.java)

    fun postLogin(username: String, password: String): LiveData<Resource<LoginPostResponse>> {
        val data = MutableLiveData<Resource<LoginPostResponse>>()
        val loginPostData = LoginPostData(username = username, password = password)

        apiService.postLogin(loginPostData).enqueue(object : Callback<LoginPostResponse> {
            override fun onResponse(call: Call<LoginPostResponse>?, response: Response<LoginPostResponse>?) {
                if (response?.isSuccessful != null && response.isSuccessful) {
                    data.value = Resource.success(response.body())
                } else {
                    val errorConverter = RetrofitClient.getClient(BASE_URL)?.responseBodyConverter<LoginPostResponse>(LoginPostResponse::class.java, arrayOfNulls<Annotation>(0))

                    if (response?.errorBody() != null) {
                        val error = errorConverter?.convert(response.errorBody())
                        data.value = Resource.failed(error)
                    }
                }

            }

            override fun onFailure(call: Call<LoginPostResponse>?, t: Throwable?) {
                data.value = Resource.error(AppException(t))
            }
        })

        return data
    }
}