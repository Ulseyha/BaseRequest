package com.pathmazing.hr.mvvm.navigator

import com.pathmazing.baserequest.mvvm.CoreNavigator
import com.pathmazing.hr.mvvm.model.LoginResponse

interface LoginNavigator : CoreNavigator {

    fun onLoginSucceed(loginResponse: LoginResponse)
}