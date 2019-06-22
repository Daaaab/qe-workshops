package com.futureprocessing.qe.workshops.register

import android.view.View
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.common.AppNavigator

class RegisterViewModel(private val appNavigator: AppNavigator) {

    val username = ObservableField("")
    val password = ObservableField("")
    val errorText = ObservableField<String>("")
    val showSpinner = ObservableField(false)



    fun registerCommand(view: View) {

    }

    fun dispose() {
    }

    private fun performUser() {

    }
}