package com.pathmazing.baserequest.mvvm

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<Navigator : BaseNavigator>{

    protected val compositeDisposable = CompositeDisposable()

    var navigator: Navigator? = null

    fun attachView(view: Navigator) {
        this.navigator = view
    }

    fun detachView() {
        compositeDisposable.clear()
        this.navigator = null
    }
}