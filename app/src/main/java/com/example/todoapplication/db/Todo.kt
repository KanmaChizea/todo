package com.example.todoapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Todo id")
    var id:Int,
    @ColumnInfo(name = "Todo task")
    var task:String,
    @ColumnInfo(name = "Todo isDone")
    var isDone : Boolean
)
