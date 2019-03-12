package com.pathmazing.baserequest.mvvm

import io.reactivex.disposables.CompositeDisposable

open class CoreViewModel<Navigator : CoreNavigator>{

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