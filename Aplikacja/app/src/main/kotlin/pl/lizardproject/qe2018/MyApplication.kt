package pl.lizardproject.qe2018

import android.app.Application
import pl.lizardproject.qe2018.database.DatabaseFacade
import pl.lizardproject.qe2018.session.UserSession

class MyApplication : Application() {
    val databaseFacade = DatabaseFacade(this)
    val userSession = UserSession()
}