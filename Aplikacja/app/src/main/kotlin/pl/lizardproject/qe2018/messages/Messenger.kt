package pl.lizardproject.qe2018.messages

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

class Messenger {
    fun showMessage(view: View, @StringRes stringId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        showMessage(view, view.context.getString(stringId), duration)
    }

    fun showMessage(view: View, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
        Snackbar.make(view, text, duration).show()
    }
}