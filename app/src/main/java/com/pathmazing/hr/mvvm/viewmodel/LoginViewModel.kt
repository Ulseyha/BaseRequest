package com.pathmazing.hr.mvvm.viewmodel

import android.content.Context
import com.pathmazing.baserequest.mvvm.CoreViewModel
import com.pathmazing.baserequest.rest.CoreDisposableSingleObserver
import com.pathmazing.baserequest.rest.RestClient
import com.pathmazing.hr.BuildConfig
import com.pathmazing.hr.mvvm.model.LoginRequest
import com.pathmazing.hr.mvvm.model.LoginResponse
import com.pathmazing.hr.mvvm.navigator.LoginNavigator
import com.pathmazing.hr.rest.RestApiService
import java.util.*

class LoginViewModel(context: Context,private var mNavigator: LoginNavigator) : CoreViewModel<LoginNavigator>() {

}