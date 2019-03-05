package com.pathmazing.hr.mvvm.model

import com.google.gson.annotations.SerializedName

class LoginResponse{

    @SerializedName("firstName")
    var firstName: String? = null
    @SerializedName("lastName")
    var lastName: String? = null
    @SerializedName("role")
    var role: String? = null
    @SerializedName("clients")
    var clients: List<Any>? = null
    @SerializedName("accessToken")
    var accessToken: String? = null
    @SerializedName("username")
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