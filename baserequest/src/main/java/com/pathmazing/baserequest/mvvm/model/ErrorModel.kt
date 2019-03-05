package com.pathmazing.baserequest.mvvm.model

import java.io.Serializable

class ErrorModel(
        val message: String,
        val code: Int,
        val status: Int
) : Serializable