package com.pathmazing.baserequest.mvvm

import com.pathmazing.baserequest.mvvm.ErrorModel

interface CoreNavigator{
    fun onShowProgress()
    fun onDismissProgress()
    fun onFailure(throwable: Throwable)
    fun onError(errorModel: ErrorModel)
    fun onConnectionError()
    fun onUnAuthorization()
}
