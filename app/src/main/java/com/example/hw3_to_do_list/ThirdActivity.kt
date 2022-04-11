package com.example.hw3_to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_third.*


class ThirdActivity : AppCompatActivity() {

    private var id: Int = -1
    private lateinit var item: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        id = intent.getIntExtra("position", -1)
        item = intent.getStringExtra("item").toString()

        edit_text.setText(item)
    }

    fun update(view: View) {
        val intent = Intent()
        intent.putExtra("id",id)
        intent.putExtra("item",edit_text.text.toString())
        setResult(RESULT_OK,intent)
        finish()
    }




    fun cancel(view: View){
        finish()
        }

    fun delete(view: View) {
        val intent = Intent()
        intent.putExtra("id",id)
        intent.putExtra("item",edit_text.text.toString())
        intent.putExtra("isDeleted",true)
        setResult(RESULT_OK, intent)
        finish()
    }
}








