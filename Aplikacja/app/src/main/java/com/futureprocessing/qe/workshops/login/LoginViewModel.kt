package com.futureprocessing.qe.workshops.login

import android.view.View
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.common.AppNavigator

class LoginViewModel(private val appNavigator: AppNavigator) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")


    fun loginCommand(view: View) {

    }

    fun registerCommand(ignored: View) {
        appNavigator.openRegisterActivity()
    }
}