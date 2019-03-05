package com.pathmazing.baserequest.ui.activity

import android.support.v7.app.AppCompatActivity
import com.pathmazing.hr.mvvm.model.ErrorModel
import com.pathmazing.hr.mvvm.navigator.BaseNavigator
import com.pathmazing.baserequest.mvvm.viewmodel.BaseViewModel

class CoreActivity : AppCompatActivity() {

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
