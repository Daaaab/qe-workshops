package pl.lizardproject.qe2018.itemlist

import android.databinding.ObservableField
import android.view.View
import android.widget.CompoundButton
import pl.lizardproject.qe2018.database.DatabaseFacade
import pl.lizardproject.qe2018.database.converter.toDbModel
import pl.lizardproject.qe2018.messages.Messenger
import pl.lizardproject.qe2018.model.Item
import pl.lizardproject.qe2018.navigation.AppNavigator

class ItemViewModel(item: Item, private val databaseFacade: DatabaseFacade, private val appNavigator: AppNavigator, private val messenger: Messenger) {

    val item = ObservableField(item)

    fun onCheckChangedCommand(view: CompoundButton, isChecked: Boolean) {
        if (item.get().isChecked != isChecked) {
            databaseFacade.saveItem(item.get().checkItem(isChecked).toDbModel())
                    .subscribe(
                            { },
                            { messenger.showMessage(view, it.message!!) })
        }
    }

    fun onDeleteClickCommand(ignored: View) {
        databaseFacade.deleteItem(item.get().toDbModel())
    }

    fun onClickCommand(ignored: View) {
        appNavigator.openEditItemActivity(item.get().id)
    }
}