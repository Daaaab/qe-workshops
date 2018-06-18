package pl.lizardproject.qe2018.itemlist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import pl.lizardproject.qe2018.MyApplication
import pl.lizardproject.qe2018.R
import pl.lizardproject.qe2018.databinding.ActivityItemListBinding
import pl.lizardproject.qe2018.navigation.AppNavigator

class ItemListActivity : AppCompatActivity() {

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { ItemListViewModel(myApplication.databaseFacade, myApplication.userSession, AppNavigator(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityItemListBinding>(this, R.layout.activity_item_list).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_item_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.logout()
        return true
    }
}