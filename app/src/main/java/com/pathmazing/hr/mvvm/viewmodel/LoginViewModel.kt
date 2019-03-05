package com.pathmazing.hr.mvvm.viewmodel

import com.pathmazing.hr.mvvm.model.LoginRequest
import com.pathmazing.hr.mvvm.model.LoginResponse
import com.pathmazing.hr.mvvm.navigator.LoginNavigator
import com.pathmazing.hr.rest.disposable.HRDisposableSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private var mNavigator: LoginNavigator) : BaseViewModel<LoginNavigator>() {

    fun onLogin() {

        val loginRequest = LoginRequest()
        loginRequest.username = "richard.houn"
        loginRequest.password = "123456"

        api.postUserLogin(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mNavigator.onShowProgress()
                }.doFinally {
                    mNavigator.onDismissProgress()
                }
                .subscribeWith(object : HRDisposableSingleObserver<LoginResponse>(navigator) {
                    override fun onSuccess(dealer: LoginResponse) {
                        mNavigator.onLoginSucceed(dealer)
                    }

                }).addTo(compositeDisposable)
    }
}