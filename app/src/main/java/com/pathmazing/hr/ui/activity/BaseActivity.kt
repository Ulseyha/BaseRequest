package com.pathmazing.hr.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pathmazing.baserequest.mvvm.model.ErrorModel
import com.pathmazing.baserequest.mvvm.navigator.BaseNavigator
import com.pathmazing.baserequest.mvvm.viewmodel.BaseViewModel
import com.pathmazing.hr.R
import com.pathmazing.hr.mvvm.model.LoginResponse
import com.pathmazing.hr.mvvm.navigator.LoginNavigator
import kotlinx.android.synthetic.main.activity_main.*

class BaseActivity : AppCompatActivity(), LoginNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_login.setOnClickListener {
//            LoginViewModel(this).onLogin()
        }
    }

    override fun onShowProgress() {

    }

    override fun onLoginSucceed(loginResponse: LoginResponse) {

        val d = BaseViewModel<BaseNavigator>()

    }

    override fun onDismissProgress() {

    }

    override fun onShowMessage(message: String) {

    }

    override fun onFailure(throwable: Throwable) {

    }

    override fun onError(errorModel: ErrorModel) {

    }

    override fun onConnectionError() {

    }
}
