package com.example.todos

import android.app.Application
import androidx.room.Room
import com.example.androidmidtermtodo.network.room.UserDataBase

class App : Application() {
    lateinit var dataBase: UserDataBase

    override fun onCreate() {
        super.onCreate()
        instance = this
        dataBase = Room.databaseBuilder(
            applicationContext,
            UserDataBase::class.java, "user_db"
        ).allowMainThreadQueries().build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}