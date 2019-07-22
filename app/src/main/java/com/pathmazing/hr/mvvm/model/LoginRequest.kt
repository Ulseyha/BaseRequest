package com.pathmazing.hr.mvvm.model

class LoginRequest{

    var password: String? = null
    var username: String? = null

    override fun toString(): String {
        return "LoginRequest{" +
                "password = '" + password + '\''.toString() +
                ",username = '" + username + '\''.toString() +
                "}"
    }
}