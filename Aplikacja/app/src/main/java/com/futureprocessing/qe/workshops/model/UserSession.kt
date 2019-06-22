package com.futureprocessing.qe.workshops.model

class UserSession {
    var user: User? = null
        private set

    fun start(user: User) {
        this.user = user
    }

    fun end() {
        user = null
    }
}