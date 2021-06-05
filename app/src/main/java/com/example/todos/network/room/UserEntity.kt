package com.example.todos.network.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val userName: String,
    @ColumnInfo(name = "phone") val phone: String
)
