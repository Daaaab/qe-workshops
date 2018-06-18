package pl.lizardproject.qe2018.session

import pl.lizardproject.qe2018.model.User

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