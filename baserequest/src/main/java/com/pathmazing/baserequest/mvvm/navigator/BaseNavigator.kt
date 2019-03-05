package com.pathmazing.baserequest.mvvm.navigator

import com.pathmazing.baserequest.mvvm.model.ErrorModel

interface BaseNavigator{

    fun onShowProgress()
    fun onDismissProgress()
    fun onShowMessage(message: String)
    fun onFailure(throwable: Throwable)
    fun onError(errorModel: ErrorModel)
    fun onConnectionError()
}
