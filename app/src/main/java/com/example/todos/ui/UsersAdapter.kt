package com.example.todos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todos.R
import com.example.todos.network.dto.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    val users = mutableListOf<User>()
    var onClick: (Int) -> Unit = {}

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id = itemView.findViewById<TextView>(R.id.userId)
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val userName = itemView.findViewById<TextView>(R.id.userName)
        private val phone = itemView.findViewById<TextView>(R.id.phone)
        private val userContainer = itemView.findViewById<CardView>(R.id.userContainer)
        fun onBind(position: Int) {
            id.text = users[position].id.toString()
            name.text = users[position].name
            userName.text = users[position].username
            phone.text = users[position].phone
            userContainer.setOnClickListener {
                onClick(users[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.user_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = users.size
}