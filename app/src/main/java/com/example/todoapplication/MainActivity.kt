package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.db.Todo
import com.example.todoapplication.db.TodoDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var newTodo : EditText
    private lateinit var addButton : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var listCheckBox: CheckBox
    private lateinit var viewModel: TodoViewModel
    private lateinit var viewAdapter: TodoRecyclerViewAdapter
    private var isClicked = false
    private lateinit var clickedTodo : Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newTodo = findViewById(R.id.etAddTodo)
        addButton = findViewById(R.id.btnAdd)
       recyclerView = findViewById(R.id.rvTodoList)
//
        val dao = TodoDatabase.getInstance(application).todoDao
        val factory = TodoViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[TodoViewModel::class.java]

        initializeRecyclerView()

        addButton.setOnClickListener {
            if(newTodo.text.toString().isNotEmpty()) addTodo()
            else Toast.makeText(this, "Can't add empty todo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewAdapter = TodoRecyclerViewAdapter({ selectedTodo: Todo -> deleteTodo(selectedTodo) },
         { selectedTodo: Todo -> updateTodo(selectedTodo) })
        recyclerView.adapter = viewAdapter
       getTodo()

    }
    private fun getTodo(){
        viewModel.todoList.observe(this){
            viewAdapter.setList(it)
            viewAdapter.notifyDataSetChanged()
        }
   }
    private fun addTodo(){
        viewModel.addTodo(Todo(0, newTodo.text.toString(),false ))
        reset()
    }
    private fun deleteTodo(selectedTodo: Todo){
        viewModel.deleteTodo(Todo(selectedTodo.id, selectedTodo.task, selectedTodo.isDone ))
    }

    private fun updateTodo(selectedTodo: Todo){
        viewModel.updateTodo(Todo(selectedTodo.id, selectedTodo.toString(), !selectedTodo.isDone ))
    }

    private fun reset(){
        newTodo.setText("")
    WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.ime())
    }
}