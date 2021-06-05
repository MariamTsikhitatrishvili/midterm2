package com.example.todos.network.dto

data class ToDo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
