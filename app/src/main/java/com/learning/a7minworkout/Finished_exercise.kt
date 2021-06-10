package com.learning.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_finished_exercise.*
import java.text.SimpleDateFormat
import java.util.*

class Finished_exercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // addingDate()
        setContentView(R.layout.activity_finished_exercise)
        goback.setOnClickListener {
            val i=Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
   /* private fun addingDate(){
        val calendar=Calendar.getInstance()
        val dateTime=calendar.time
        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.i("date : ",date)
        val dbHandler=SQLiteopenhelper(this,null)
        dbHandler.addDate(date)
    }*/
}