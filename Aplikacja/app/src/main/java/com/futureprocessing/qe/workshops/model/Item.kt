package com.futureprocessing.qe.workshops.model

class Item(val id: Int?, val name: String, val category: Category, val priority: Priority, val userId: Int, val isChecked: Boolean = false) {

    fun checkItem(check: Boolean) = Item(id, name, category, priority, userId, check)
}