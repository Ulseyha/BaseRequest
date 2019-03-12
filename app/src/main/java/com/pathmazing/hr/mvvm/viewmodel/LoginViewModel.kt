//package com.pathmazing.hr.mvvm.viewmodel
//
//import com.pathmazing.baserequest.mvvm.viewmodel.BaseViewModel
//import com.pathmazing.hr.mvvm.navigator.LoginNavigator
//
//class LoginViewModel(private var mNavigator: LoginNavigator) : BaseViewModel<LoginNavigator>() {
//
//    fun onLogin() {
//
////        val loginRequest = LoginRequest()
////        loginRequest.username = "richard.houn"
////        loginRequest.password = "123456"
//
////        api.postUserLogin(loginRequest)
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .doOnSubscribe {
////                    mNavigator.onShowProgress()
////                }.doFinally {
////                    mNavigator.onDismissProgress()
////                }
////                .subscribeWith(object : HRDisposableSingleObserver<LoginResponse>(navigator) {
////                    override fun onSuccess(dealer: LoginResponse) {
////                        mNavigator.onLoginSucceed(dealer)
////                    }
////
////                }).addTo(compositeDisposable)
//    }
//}