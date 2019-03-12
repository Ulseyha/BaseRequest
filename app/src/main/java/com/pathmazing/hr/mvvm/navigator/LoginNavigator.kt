package com.pathmazing.hr.mvvm.navigator

import com.pathmazing.baserequest.mvvm.navigator.BaseNavigator
import com.pathmazing.hr.mvvm.model.LoginResponse

interface LoginNavigator : BaseNavigator {
    fun onLoginSucceed(loginResponse: LoginResponse)
}