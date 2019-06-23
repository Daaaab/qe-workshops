package com.futureprocessing.qe.workshops.databinding

import android.widget.Spinner
import androidx.databinding.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.futureprocessing.qe.workshops.itemlist.ItemRecyclerViewAdapter
import com.futureprocessing.qe.workshops.messages.Messenger
import com.futureprocessing.qe.workshops.model.Item
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("items")
fun bindRecyclerViewItems(view: RecyclerView, list: ObservableArrayList<Item>) {
    if (view.adapter == null) {
        view.layoutManager = LinearLayoutManager(view.context)
        view.adapter = ItemRecyclerViewAdapter(list, Messenger(), view.context)
        view.addItemDecoration(DividerItemDecoration(view.context, (view.layoutManager as LinearLayoutManager).orientation))
    } else {
        view.adapter?.notifyDataSetChanged()
    }
}

@InverseBindingMethods(InverseBindingMethod(type = Spinner::class, attribute = "android:selectedItemPosition"))
class SpinnerBindingAdapters

@BindingMethods(value = [BindingMethod(type = TextInputLayout::class, attribute = "app:errorText", method = "setError")])
class TextInputLayoutBindingAdapters