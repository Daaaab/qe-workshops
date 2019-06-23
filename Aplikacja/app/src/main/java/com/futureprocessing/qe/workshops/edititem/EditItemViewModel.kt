package com.futureprocessing.qe.workshops.edititem

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.database.toAppModel
import com.futureprocessing.qe.workshops.database.toDbModel
import com.futureprocessing.qe.workshops.messages.Messenger
import com.futureprocessing.qe.workshops.model.Category
import com.futureprocessing.qe.workshops.model.Item
import com.futureprocessing.qe.workshops.model.Priority
import com.futureprocessing.qe.workshops.model.UserSession
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EditItemViewModel(private val itemId: Int?, private val databaseFacade: AppDatabase, private val userSession: UserSession, private val appNavigator: AppNavigator, private val messenger: Messenger) {

    val newItemName = ObservableField("")
    val newItemCategoryPosition = ObservableField(Category.FRUITS.ordinal)
    val newItemPriorityPosition = ObservableField(Priority.NORMAL.ordinal)

    private val disposable = CompositeDisposable()

    private var subscription = databaseFacade.itemDao().getItem(itemId ?: -1)
        .observeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
            .map { it.toAppModel() }
            .subscribe({
                newItemName.set(it.name)
                newItemCategoryPosition.set(it.category.ordinal)
                newItemPriorityPosition.set(it.priority.ordinal)
            }, {err->
                Log.e("DB_MSG_A", err.message)
            })

    val categories = Category.values().map { it.toString().toLowerCase() }
    val priorities = Priority.values().map { it.toString().toLowerCase() }

    fun saveItemCommand(view: View) {
        newItemName.get()?.let {name ->
            if (name.isNotBlank()) {
                val nameString =  newItemName.get() ?: ""
                val categoryIndex = newItemCategoryPosition.get() ?: 0
                val priorityIndex = newItemPriorityPosition.get() ?: 0

                val dbItem = Item(itemId,nameString, Category.values()[categoryIndex], Priority.values()[priorityIndex], userSession.user!!.id!!).toDbModel()

                val sub = Single.just(dbItem)
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess {entity ->
                        Log.e("DB_MSG", "Insert ${entity.name} for user: ${userSession.user?.id} - ${userSession.user?.login}")
                        databaseFacade.itemDao().insert(entity)
                    }
                    .subscribe({
                        appNavigator.openItemListActivity()
                    },{err->
                        messenger.showMessage(view, err.message!!)
                        Log.e("DB_MSG_B", err.message)
                    })

                disposable.add(sub)

            } else {
                messenger.showMessage(view, R.string.editItemErrorEmptyName)
            }
        }

    }

    fun dispose() {
        subscription.dispose()
        disposable.dispose()
    }
}