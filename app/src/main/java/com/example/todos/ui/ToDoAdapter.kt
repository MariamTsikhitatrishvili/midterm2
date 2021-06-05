package com.example.todos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.R
import com.example.todos.network.dto.ToDo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    val todos = mutableListOf<ToDo>()

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userId = itemView.findViewById<TextView>(R.id.userId)
        private val id = itemView.findViewById<TextView>(R.id.id)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val completed = itemView.findViewById<TextView>(R.id.completed)
        fun onBind(position: Int) {
            userId.text = todos[position].userId.toString()
            id.text = todos[position].id.toString()
            title.text = todos[position].title
            completed.text = todos[position].completed.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TodoViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.todo_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = todos.size
}