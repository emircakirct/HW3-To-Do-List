package com.example.hw3_to_do_list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import java.util.ArrayList

class SecondActivity : AppCompatActivity() {
    private val listToDo = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun goBack(view : View)
    {
        finish()
    }

    fun saveAndAdd(view : View)
    {
        if(task_text.text.isBlank())
            Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show()
        else {
            listToDo.add(task_text.text.toString())
            task_text.text.clear()
            Toast.makeText(this, "Saved!!", Toast.LENGTH_SHORT).show()

        }
    }

    fun saveAndGoBack(view : View)
    {
        if(task_text.text.isBlank())
            Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show()
        else {
              val intent = Intent(this,MainActivity::class.java)
              listToDo.add(task_text.text.toString())
               task_text.text.clear()
              intent.putExtra("tasks",listToDo)
               setResult(Activity.RESULT_OK, intent)
               finish()

        }
    }

    fun clear(view: View)
    {
        task_text.text.clear()
    }
}