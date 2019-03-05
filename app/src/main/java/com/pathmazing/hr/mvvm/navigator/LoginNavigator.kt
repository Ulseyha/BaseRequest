package com.pathmazing.hr.mvvm.navigator

import com.pathmazing.hr.mvvm.model.LoginResponse

interface LoginNavigator {
    fun onLoginSucceed(loginResponse: LoginResponse)
}