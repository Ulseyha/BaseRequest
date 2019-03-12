package com.pathmazing.hr.mvvm.viewmodel

import android.content.Context
import com.pathmazing.baserequest.mvvm.CoreNavigator
import com.pathmazing.baserequest.mvvm.CoreViewModel
import com.pathmazing.baserequest.rest.CoreDisposableSingleObserver
import com.pathmazing.baserequest.rest.RestClient
import com.pathmazing.hr.mvvm.model.LoginRequest
import com.pathmazing.hr.mvvm.model.LoginResponse
import com.pathmazing.hr.mvvm.navigator.LoginNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private var context: Context,private var mNavigator: LoginNavigator) : CoreViewModel<LoginNavigator>() {

//    val api = RestClient.Builder(context).baseUrl()

    fun onLogin() {

        val loginRequest = LoginRequest()
        loginRequest.username = "richard.houn"
        loginRequest.password = "123456"

//        api.postUserLogin(loginRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe {
//                    mNavigator.onShowProgress()
//                }.doFinally {
//                    mNavigator.onDismissProgress()
//                }
//                .subscribeWith(object : CoreDisposableSingleObserver<LoginResponse>(navigator) {
//                    override fun onSuccess(dealer: LoginResponse) {
//                        mNavigator.onLoginSucceed(dealer)
//                    }
//
//                }).addTo(compositeDisposable)
    }
}