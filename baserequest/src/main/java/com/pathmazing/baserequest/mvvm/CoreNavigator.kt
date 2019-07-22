package com.pathmazing.baserequest.mvvm

interface CoreNavigator{
    fun onShowProgress(){}
    fun onDismissProgress(){}
    fun onFailure(throwable: Throwable)
    fun onError(errorModel: ErrorModel)
    fun onConnectionError()
    fun onUnAuthorization(){}
}
