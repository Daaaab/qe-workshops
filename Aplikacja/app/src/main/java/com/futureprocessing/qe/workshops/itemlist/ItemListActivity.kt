package com.futureprocessing.qe.workshops.itemlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.futureprocessing.qe.workshops.MyApplication
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.databinding.ActivityItemListBinding


class ItemListActivity : AppCompatActivity() {

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { ItemListViewModel(myApplication.db, myApplication.session, AppNavigator(this)) }

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