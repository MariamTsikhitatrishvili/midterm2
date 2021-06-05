package com.example.todos.network

import com.example.todos.network.dto.ToDo
import com.example.todos.network.dto.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("users")
    fun getUsers():Call<List<User>>

    @GET("users/{userID}/todos")
    fun getTodoById(@Path("userID") id:Int):Call<List<ToDo>>
}