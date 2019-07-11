package com.pathmazing.hr.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pathmazing.baserequest.mvvm.ErrorModel
import com.pathmazing.hr.R
import com.pathmazing.hr.mvvm.model.LoginResponse
import com.pathmazing.hr.mvvm.navigator.LoginNavigator

class BaseActivity : AppCompatActivity(), LoginNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        button_login.setOnClickListener {
//            LoginViewModel(this,this).onLogin()
//        }
    }

    override fun onLoginSucceed(loginResponse: LoginResponse) {
    }

    override fun onConnectionError() {
    }

    override fun onDismissProgress() {
    }

    override fun onError(errorModel: ErrorModel) {
    }

    override fun onFailure(throwable: Throwable) {
    }

    override fun onShowProgress() {
    }

    override fun onUnAuthorization() {
    }

}
