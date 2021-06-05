package com.example.todos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todos.network.dto.User
import com.example.todos.network.ApiClient
import com.example.todos.network.room.UserEntity
import com.example.todos.ui.UsersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {

    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        val count = findViewById<TextView>(R.id.totalUsersTextView)
        initRecycler()
        getUsers {
            adapter.users.addAll(it)
            adapter.notifyDataSetChanged()
            count.text = it.size.toString()
            App.instance.dataBase.userDao().insertUsers(it.map { user -> user.toEntity() })
        }
        swipeRefreshLayout.setOnRefreshListener { refreshLayout() }

        adapter.onClick = {
            getToDoById(it)
        }
    }

    private fun initRecycler() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter()
        usersRecyclerView.adapter = adapter
    }

    private fun getUsers(onResponse: (List<User>) -> Unit) {
        ApiClient.getService().getUsers().enqueue(callback(onResponse))
    }

    private fun callback(onResponse: (List<User>) -> Unit) = object : Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            response.body()?.let { onResponse(it) }
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
        }

    }

    private fun getToDoById(userPosition: Int) {
        val intent = Intent(this, ToDoActivity::class.java).apply {
            putExtra(KEY_USER, adapter.users[userPosition - 1])
        }
        startActivity(intent)
    }

    private fun refreshLayout() {
        adapter.users.clear()
        swipeRefreshLayout.isRefreshing = true
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.users.addAll(getUsersFromRoom())
            adapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }, 3000)
    }

    private fun getUsersFromRoom(): List<User> {
        return App.instance.dataBase.userDao().getUsers()
            .map { User(it.id, it.name, it.userName, it.phone) }
    }

    companion object {
        const val KEY_USER = "user"
    }
}

fun User.toEntity() = UserEntity(id, name, username, phone)
