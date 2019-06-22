package com.futureprocessing.qe.workshops.login

import android.view.View
import androidx.databinding.ObservableField

class LoginViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
//
//    private var subscription: Subscription? = null

    fun loginCommand(view: View) {

    }

    fun registerCommand(ignored: View) {

    }

    fun dispose() {

    }
}