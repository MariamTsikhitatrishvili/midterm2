package com.example.todos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.network.ApiClient
import com.example.todos.network.dto.ToDo
import com.example.todos.network.dto.User
import com.example.todos.ui.TodoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoActivity : AppCompatActivity() {
    private var idForTodo = 0
    private lateinit var adapter: TodoAdapter
    private lateinit var todosRecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        todosRecycler = findViewById(R.id.todoRecyclerView)
        val userId = findViewById<TextView>(R.id.userId)
        val name = findViewById<TextView>(R.id.name)
        val userName = findViewById<TextView>(R.id.userName)
        val phone = findViewById<TextView>(R.id.phone)
        val todoCount = findViewById<TextView>(R.id.todoCount)

        val user = intent.extras?.getParcelable<User>(UsersActivity.KEY_USER)

        user?.let {
            idForTodo = it.id
            userId.text = it.id.toString()
            name.text = it.name
            userName.text = it.username
            phone.text = it.phone
        }

        initAdapter()

        getTodos {
            adapter.todos.addAll(it)
            adapter.notifyDataSetChanged()
            todoCount.text = it.size.toString()
        }
    }

    private fun initAdapter() {
        adapter = TodoAdapter()
        todosRecycler.layoutManager = LinearLayoutManager(this)
        todosRecycler.adapter = adapter

    }

    private fun getTodos(onResponse: (List<ToDo>) -> Unit) {
        ApiClient.getService().getTodoById(idForTodo).enqueue(object : Callback<List<ToDo>> {
            override fun onResponse(call: Call<List<ToDo>>, response: Response<List<ToDo>>) {
                response.body()?.let { onResponse(it) }
            }

            override fun onFailure(call: Call<List<ToDo>>, t: Throwable) {

            }

        })
    }

}