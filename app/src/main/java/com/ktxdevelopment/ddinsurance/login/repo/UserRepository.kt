package com.ktxdevelopment.ddinsurance.login.repo

sealed class User {
    data class LoggedInUser(val email: String, val password: String) : User()
    object GuestUser : User()
    object NoUserLoggedIn : User()
}

object UserRepository {

    private var _user: User = User.NoUserLoggedIn
    val user: User
        get() = _user

    fun signIn(email: String, password: String) {
        _user = User.LoggedInUser(email, password)
    }

    fun signUp(email: String, password: String) {
        _user = User.LoggedInUser(email, password)
    }

    fun signInAsGuest() {
        _user = User.GuestUser
    }

    fun isKnownUserEmail(email: String): Boolean {
        return !email.contains("signup")
    }
}
