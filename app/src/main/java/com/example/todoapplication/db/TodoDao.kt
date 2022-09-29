package com.example.todoapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert
   suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
   suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo_list")
    fun getTodo() : LiveData<List<Todo>>
}