package com.pathmazing.baserequest.rest.disposable

import com.google.gson.Gson
import com.pathmazing.baserequest.mvvm.model.ErrorModel
import com.pathmazing.baserequest.mvvm.navigator.BaseNavigator
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.io.IOException

abstract class HRDisposableSingleObserver<T>(private var baseNavigator: BaseNavigator?) : DisposableSingleObserver<T>() {

    override fun onError(throwable: Throwable) {
        if (throwable is IOException) {
            baseNavigator?.onConnectionError()
            return
        }
        if (throwable is HttpException) {
            if (throwable.code() == 401) {
                onUnAuthorization()
                return
            }
            try {
                val responseBody = throwable.response().errorBody()?.string()
                val errorModel = Gson().fromJson<ErrorModel>(responseBody,ErrorModel::class.java)
                onServerError(errorModel)
            } catch (e: Exception) {
                onOtherError(throwable)
            }
            return
        }
        onOtherError(throwable)
    }

    open fun onUnAuthorization() {
//        baseNavigator?.onUnAuthorization()
    }

    open fun onOtherError(throwable: Throwable) {
        baseNavigator?.onFailure(throwable)
    }

    open fun onServerError(errorModel: ErrorModel) {
        baseNavigator?.onError(errorModel)
    }

}