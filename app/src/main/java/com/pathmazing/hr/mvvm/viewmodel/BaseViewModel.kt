package com.pathmazing.hr.mvvm.viewmodel

import com.pathmazing.hr.BuildConfig
import com.pathmazing.hr.mvvm.navigator.BaseNavigator
import com.pathmazing.hr.rest.RestClient
import com.pathmazing.hr.rest.service.ApiService
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<Navigator : BaseNavigator>{

    protected val compositeDisposable = CompositeDisposable()
    protected val api = RestClient.getApiService<ApiService>(BuildConfig.BASE_URL)

    var navigator: Navigator? = null

    fun attachView(view: Navigator) {
        this.navigator = view
    }

    fun detachView() {
        compositeDisposable.clear()
        this.navigator = null
    }
}