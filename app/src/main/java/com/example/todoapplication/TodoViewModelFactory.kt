package com.example.todoapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapplication.db.TodoDao
import java.lang.IllegalArgumentException

class TodoViewModelFactory(private val dao:TodoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TodoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
    }

