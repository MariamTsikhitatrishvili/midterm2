package com.example.todos.network.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): MutableList<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers (users: List<UserEntity>)
}