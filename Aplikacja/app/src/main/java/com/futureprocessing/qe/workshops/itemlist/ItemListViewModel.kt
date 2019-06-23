package com.futureprocessing.qe.workshops.itemlist

import android.util.Log
import android.view.View
import androidx.databinding.ObservableArrayList
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.database.toAppModel
import com.futureprocessing.qe.workshops.model.Item
import com.futureprocessing.qe.workshops.model.UserSession
import io.reactivex.schedulers.Schedulers

class ItemListViewModel(databaseFacade: AppDatabase, private val userSession: UserSession, private val appNavigator: AppNavigator) {

    val items = ObservableArrayList<Item>()

    private var subscription = databaseFacade.itemDao().getItems(userSession.user!!.id!!)
        .observeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
            .map { itemsList -> itemsList.map { item -> item.toAppModel() } }
            .subscribe({ values ->
                items.clear()
                items.addAll(values)
            }, { err -> Log.e("DB_MSG", err.message, err) })

    fun addItemCommand(ignored: View) {
       appNavigator.openEditItemActivity()
    }

    fun logout() {
        userSession.end()
        appNavigator.openLoginActivity()
    }

    fun dispose() {
        subscription.dispose()
    }
}