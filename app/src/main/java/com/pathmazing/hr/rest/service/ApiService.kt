package com.pathmazing.hr.rest.service

import com.pathmazing.hr.mvvm.model.LoginRequest
import com.pathmazing.hr.mvvm.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun postUserLogin(
            @Body loginRequest: LoginRequest
    ): Single<LoginResponse>
}