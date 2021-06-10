package com.learning.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbar_exercise_activity
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar= supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        getAllCompleted()

    }
    private fun getAllCompleted(){
        val dbhandler=SQLiteopenhelper(this,null)
        val list_completed=dbhandler.getAllCompleted()
        for (i in list_completed){
        Log.i("Ele present",i)}

        if(list_completed.size>0){
            rvhistory.visibility= View.VISIBLE
            noavailable.visibility=View.GONE

            rvhistory.layoutManager=LinearLayoutManager(this)

            rvhistory.adapter=historyadapter(this,list_completed)

        }
        else{
            rvhistory.visibility=View.GONE
            noavailable.visibility=View.VISIBLE
        }
    }
}