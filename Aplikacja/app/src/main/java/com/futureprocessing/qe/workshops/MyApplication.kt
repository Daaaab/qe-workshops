package com.futureprocessing.qe.workshops

import android.app.Application
import androidx.room.Room
import com.futureprocessing.qe.workshops.database.AppDatabase
import com.futureprocessing.qe.workshops.model.UserSession

class MyApplication : Application() {

    lateinit var db: AppDatabase
    val session = UserSession()

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "qe-workshops"
        ).build()
    }
}