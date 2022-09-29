package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.db.Todo


class TodoRecyclerViewAdapter(private val deleteOnClickListener: (Todo) -> Unit,
                              private val cardOnClickListener:(Todo) -> Unit) : RecyclerView.Adapter<TodoViewHolder>(){
   private var todoList = ArrayList<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       holder.bind(todoList[position], deleteOnClickListener, cardOnClickListener)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setList(todos:List<Todo>){
        todoList.clear()
        todoList.addAll(todos)
    }
}

class TodoViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(todo: Todo,deleteOnClickListener: (Todo) -> Unit,
             cardOnClickListener:(Todo) -> Unit){
        val todoCheckBoxTile = view.findViewById<CheckBox>(R.id.cBTodoItem)
        val deleteButton = view.findViewById<ImageButton>(R.id.btnDelete)

        todoCheckBoxTile.text = todo.task
        todoCheckBoxTile.isChecked = todo.isDone

        todoCheckBoxTile.setOnCheckedChangeListener{_,_ -> cardOnClickListener(todo) }
        deleteButton.setOnClickListener {
            deleteOnClickListener(todo)
        }
    }
    companion object {
        fun create(parent: ViewGroup): TodoViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.tile
                    , parent, false)
            return TodoViewHolder(view)
        }
}}