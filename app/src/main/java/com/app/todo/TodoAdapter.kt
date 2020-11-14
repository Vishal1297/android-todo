package com.app.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.todo.data.Todo
import kotlinx.android.synthetic.main.activity_todo_item.view.*

class TodoAdapter(
    private val allTodos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_todo_item, parent, false
            )
        )
    }

    fun addTodo(todo: Todo) {
        allTodos.add(todo)
        notifyItemInserted(allTodos.size - 1)
    }

    fun removeTodo() {
        allTodos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todoTitleTV: TextView, isChecked: Boolean) {
        if (isChecked) {
            todoTitleTV.paintFlags = STRIKE_THRU_TEXT_FLAG
        } else {
            todoTitleTV.paintFlags = STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = allTodos[position]
        holder.itemView.apply {
            todoTitleTV.text = currentItem.title
            doneCb.isChecked = currentItem.isChecked
            toggleStrikeThrough(todoTitleTV, currentItem.isChecked)
            doneCb.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitleTV, isChecked)
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return allTodos.size
    }

}