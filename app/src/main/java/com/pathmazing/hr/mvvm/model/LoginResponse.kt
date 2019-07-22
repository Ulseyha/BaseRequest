package com.pathmazing.hr.mvvm.model

class LoginResponse{

    var firstName: String? = null
    var lastName: String? = null
    var role: String? = null
    var clients: List<Any>? = null
    var accessToken: String? = null
    var username: String? = null

    override fun toString(): String {
        return "LoginResponse{" +
                "firstName = '" + firstName + '\''.toString() +
                ",lastName = '" + lastName + '\''.toString() +
                ",role = '" + role + '\''.toString() +
                ",clients = '" + clients + '\''.toString() +
                ",accessToken = '" + accessToken + '\''.toString() +
                ",username = '" + username + '\''.toString() +
                "}"
    }
}