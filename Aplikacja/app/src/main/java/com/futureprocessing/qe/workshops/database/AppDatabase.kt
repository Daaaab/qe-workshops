package com.futureprocessing.qe.workshops.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.futureprocessing.qe.workshops.database.dao.ItemDao
import com.futureprocessing.qe.workshops.database.dao.UserDao
import com.futureprocessing.qe.workshops.database.model.ItemEntity
import com.futureprocessing.qe.workshops.database.model.UserEntity

@Database(entities = [UserEntity::class, ItemEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
}