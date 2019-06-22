package com.futureprocessing.qe.workshops.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.futureprocessing.qe.workshops.database.dao.UserDao
import com.futureprocessing.qe.workshops.database.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}