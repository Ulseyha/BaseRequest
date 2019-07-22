package com.pathmazing.baserequest.rest

import com.google.gson.Gson
import com.pathmazing.baserequest.mvvm.ErrorModel
import com.pathmazing.baserequest.mvvm.CoreNavigator
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.io.IOException

abstract class CoreDisposableSingleObserver<T>(private var coreNavigator: CoreNavigator?) : DisposableSingleObserver<T>() {

    override fun onError(throwable: Throwable) {
        if (throwable is IOException) {
            coreNavigator?.onConnectionError()
            return
        }
        if (throwable is HttpException) {
            if (throwable.code() == 401) {
                onUnAuthorization()
                return
            }
            try {
                val responseBody = throwable.response().errorBody()?.string()
                val errorModel = Gson().fromJson<ErrorModel>(responseBody, ErrorModel::class.java)
                onServerError(errorModel)
            } catch (e: Exception) {
                onOtherError(throwable)
            }
            return
        }
        onOtherError(throwable)
    }

    open fun onUnAuthorization() {
        coreNavigator?.onUnAuthorization()
    }

    open fun onOtherError(throwable: Throwable) {
        coreNavigator?.onFailure(throwable)
    }

    open fun onServerError(errorModel: ErrorModel) {
        coreNavigator?.onError(errorModel)
    }

}