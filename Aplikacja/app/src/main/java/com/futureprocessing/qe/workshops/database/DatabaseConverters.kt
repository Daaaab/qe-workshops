package com.futureprocessing.qe.workshops.database

import androidx.room.TypeConverter
import com.futureprocessing.qe.workshops.database.model.ItemEntity
import com.futureprocessing.qe.workshops.database.model.UserEntity
import com.futureprocessing.qe.workshops.model.Category
import com.futureprocessing.qe.workshops.model.Item
import com.futureprocessing.qe.workshops.model.Priority
import com.futureprocessing.qe.workshops.model.User
import java.lang.Exception

fun UserEntity.toAppModel(): User{
    if(login != null && password != null){
        return User(login, password, id)
    }else {
        throw Exception("Database error: $login - $password");
    }
}

fun User.toDbModel() = UserEntity(id ?: 0, login, password)

fun ItemEntity.toAppModel(): Item {
   return Item(id, name, category, priority, userId, isChecked)
}

fun Item.toDbModel() = ItemEntity(id ?: 0, name, isChecked, category, priority, userId)


class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromCategory(value: Category) = value.name

        @TypeConverter
        @JvmStatic
        fun toCategory(value: String) = Category.valueOf(value)

        @TypeConverter
        @JvmStatic
        fun fromPriority(value: Priority) = value.name

        @TypeConverter
        @JvmStatic
        fun toPriority(value: String) = Priority.valueOf(value)
    }
}