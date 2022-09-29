package com.example.todoapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.db.Todo
import com.example.todoapplication.db.TodoDao
import kotlinx.coroutines.launch

class TodoViewModel(private val dao:TodoDao) : ViewModel() {

    val todoList = dao.getTodo()

    fun addTodo(todo: Todo) = viewModelScope.launch{
        dao.addTodo(todo)
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch{
        dao.updateTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        dao.deleteTodo(todo)
    }
}