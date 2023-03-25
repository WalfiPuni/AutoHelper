package com.example.autohelper.data.users


data class SignUpData(
    val login: String,
    val name: String,
    val password: String,
    val repeatpassword: String
){
    fun validate(): String?{
        if (name.isBlank()) {return "Name is empty"}
        if (login.isBlank()) {return "Login is empty"}
        if (password.isBlank()) {return "Password is empty"}
        if (repeatpassword.isBlank()) {return "Repeat password"}
        if (password != repeatpassword) {return "Passwords don't match"}
        return null
    }
}
