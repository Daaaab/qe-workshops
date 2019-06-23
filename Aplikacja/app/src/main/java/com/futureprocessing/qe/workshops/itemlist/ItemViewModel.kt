package com.futureprocessing.qe.workshops.itemlist

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.database.toDbModel
import com.futureprocessing.qe.workshops.messages.Messenger
import com.futureprocessing.qe.workshops.model.Item
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ItemViewModel(item: Item, private val databaseFacade: AppDatabase, private val appNavigator: AppNavigator, private val messenger: Messenger) {

    val item = ObservableField(item)

    fun onCheckChangedCommand(view: CompoundButton, isChecked: Boolean) {
        item.get()?.let {
            if (it.isChecked != isChecked) {
                Single.just(it.checkItem(isChecked).toDbModel())
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe({entity ->
                        databaseFacade.itemDao().insert(entity)
                    },{err->
                        Log.e("DB_MSG", err.message)
                    })
            }
        }
    }

    fun onDeleteClickCommand(ignored: View) {
        item.get()?.let {
            Single.just(it.toDbModel())
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({entity ->
                    databaseFacade.itemDao().delete(entity)
                },{err ->
                    Log.e("DB_MSG", err.message)
                })

        }
    }

    fun onClickCommand(ignored: View) {
//        appNavigator.openEditItemActivity(item.get().id)
    }
}