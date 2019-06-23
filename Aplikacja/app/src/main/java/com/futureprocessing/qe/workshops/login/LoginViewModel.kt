package com.futureprocessing.qe.workshops.login

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.database.model.UserEntity
import com.futureprocessing.qe.workshops.database.toAppModel
import com.futureprocessing.qe.workshops.model.User
import com.futureprocessing.qe.workshops.model.UserSession
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LoginViewModel(
    private val databaseFacade: AppDatabase,
    private val userSession: UserSession,
    private val appNavigator: AppNavigator
) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)

    private var subscription: Disposable? = null

    fun loginCommand(view: View) {

        if (username.get().isNullOrBlank() && password.get().isNullOrBlank()) {
            errorText.set(view.context.getString(R.string.registerError))
        } else {

            val usernameString = username.get() ?: ""
            val passwordString = password.get() ?: ""

            subscription = Single.just(
                User(usernameString, passwordString)
            ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSubscribe {
                    showSpinner.set(true)
                }
                .flatMap {
                    databaseFacade.userDao().findByName(it.login)
                }
                .flatMap {
                    if (it.password != passwordString) {
                        Log.e("DB_MSG", "Wrong password")
                        Single.error<UserEntity>(Exception(view.context.getString(R.string.loginError)))
                    } else {
                        Single.just(it)
                    }
                }
                .subscribe(
                    {
                        showSpinner.set(false)
                        errorText.set(null)
                        performUser(it.toAppModel())
                        Log.d("DB_MSG", "User logged in: ${it.login}")
                    },
                    {
                        showSpinner.set(false)
                        errorText.set(view.context.getString(R.string.loginError))
                        Log.e("DB_MSG", it.message)
                    })

        }
    }

    private fun performUser(user: User) {
        userSession.start(user)
        appNavigator.openItemListActivity()
    }

    fun dispose() {
        subscription?.dispose()
    }

    fun registerCommand(ignored: View) {
        appNavigator.openRegisterActivity()
    }
}