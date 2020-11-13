package com.app.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.todo.data.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        todos_rv.adapter = todoAdapter
        todos_rv.layoutManager = LinearLayoutManager(this)

        add_todo_btn.setOnClickListener {
            val todoTitle = todo_title_et.text.toString().trim()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                todo_title_et.text.clear()
            }
        }

        remove_todo_btn.setOnClickListener {
            todoAdapter.removeTodo()
        }
    }
}