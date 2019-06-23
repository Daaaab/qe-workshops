package com.futureprocessing.qe.workshops.itemlist

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.futureprocessing.qe.workshops.MyApplication
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.databinding.ItemItemListBinding
import com.futureprocessing.qe.workshops.messages.Messenger
import com.futureprocessing.qe.workshops.model.Item

class ItemRecyclerViewAdapter(private val list: ObservableArrayList<Item>, private val messenger: Messenger, context: Context)
    : RecyclerView.Adapter<ItemRecyclerViewAdapter.BindingViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private val databaseFacade = (context.applicationContext as MyApplication).db
    private val appNavigator = AppNavigator(context as Activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BindingViewHolder(layoutInflater.inflate(R.layout.item_item_list, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binder.viewModel = ItemViewModel(list[position], databaseFacade, appNavigator, messenger)
        holder.binder.executePendingBindings()
    }

    override fun getItemCount() = list.count()

    class BindingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binder: ItemItemListBinding = DataBindingUtil.bind(view)!!
    }
}