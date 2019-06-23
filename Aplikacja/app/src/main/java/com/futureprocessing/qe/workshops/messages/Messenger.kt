package com.futureprocessing.qe.workshops.messages

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

class Messenger {
    fun showMessage(view: View, @StringRes stringId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        showMessage(view, view.context.getString(stringId), duration)
    }

    fun showMessage(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, text, duration).show()
    }
}