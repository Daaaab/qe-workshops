package com.futureprocessing.qe.workshops.register

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.database.model.UserEntity
import com.futureprocessing.qe.workshops.database.toAppModel
import com.futureprocessing.qe.workshops.database.toDbModel
import com.futureprocessing.qe.workshops.model.User
import com.futureprocessing.qe.workshops.model.UserSession

import rx.Single
import rx.Subscription
import rx.schedulers.Schedulers

class RegisterViewModel(
    private val databaseFacade: AppDatabase,
    private val userSession: UserSession,
    private val appNavigator: AppNavigator
) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    private var subscription: Subscription? = null

    fun registerCommand(view: View) {

        if (username.get().isNullOrBlank() && password.get().isNullOrBlank()) {
            errorText.set(view.context.getString(R.string.registerError))
        } else {

            val usernameString = username.get() ?: ""
            val passwordString = password.get() ?: ""

            subscription = Single.just(
                User(usernameString, passwordString).toDbModel()
            ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSuccess {
                    showSpinner.set(true)
                    databaseFacade.userDao().insert(it)
                }
                .subscribe(
                    {
                        showSpinner.set(false)
                        errorText.set(null)
                        performUser(it.toAppModel())
                        Log.d("DB_MSG", "User created: ${it.login}")
                    },
                    {
                        showSpinner.set(false)
                        errorText.set(view.context.getString(R.string.registerErrorUserExists))
                        Log.e("DB_MSG", it.message)
                    })


        }
    }

    fun dispose() {
        subscription?.unsubscribe()
    }

    private fun performUser(user: User) {
        userSession.start(user)
    }
}