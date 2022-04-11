package com.example.hw3_to_do_list


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import android.view.View
import android.widget.ArrayAdapter
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.util.Log


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 333
    private val FILE_NAME = "TaskList"
    private val REQUEST_CODE2 = 156
    private val taskList = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String>
    private val TAG = "SAVED!"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadTheData()

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList)

        // set the adapter to listview
        task_list.adapter = adapter


        task_list.setOnItemClickListener setOnItemLongClickListener@{ parent, view, position, id ->
            val intent = Intent(this,ThirdActivity::class.java)
            intent.putExtra("position",position)
            intent.putExtra("item",taskList.get(position))
            startActivityForResult(intent,REQUEST_CODE2)
            return@setOnItemLongClickListener
        }
    }


    fun openSecondActivity(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val todoList = data?.getStringArrayListExtra("tasks")
            if (todoList != null) {
                for (task in todoList) {
                    adapter =
                        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList)
//
//                // set the adapter to listview
                    task_list.adapter = adapter
                    taskList.add(task)
                    //save
                    saveTheData()


                }
            }
        } else {

            val id = data?.getIntExtra("id", -1) ?: -1
            val isDeleted = data?.getBooleanExtra("isDeleted", false) ?: false

            if (id != -1) {
                if (isDeleted) {
                    taskList.removeAt(id)
                } else {
                    val item = data!!.getStringExtra("item")!!
                    taskList[id] = item
                }
                adapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskList)
//                // set the adapter to listview
                task_list.adapter = adapter

            }

        }
    }

    fun saveTheData() {
        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(taskList)
        editor.putString("task", json)
        editor.apply()
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()

    }

    fun loadTheData() {

        val sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val tasks = sharedPreferences.getString("tasks", "") ?:""
            if (tasks.isNotBlank()) {
                val gson = Gson()
                val s = object : TypeToken<List<String>>() {}.type
                val savedList = gson.fromJson<List<String>>(tasks, s)
                taskList.clear()
                    taskList.add(tasks)
                    Log.d(TAG, tasks)
                }

        }
    }

