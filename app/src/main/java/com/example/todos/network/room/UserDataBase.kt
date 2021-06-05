package com.example.androidmidtermtodo.network.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todos.network.room.UserDao
import com.example.todos.network.room.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}