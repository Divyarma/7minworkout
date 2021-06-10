 package com.learning.a7minworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_box.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {
    private var restTimer :CountDownTimer?=null
    private var restProgress =0
    private var exerciseTimer :CountDownTimer?=null
    private var exerciseProgress =0
    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1
    private var player :MediaPlayer?=null
    private var exerciseAdapter :ExerciseStatusAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar= supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            customback()
        }
        exerciseList=Constants.defaultExerciseList()
        llExerciseView.visibility=View.GONE
        llRestView.visibility=View.VISIBLE
        setuprestview()
        setupExerciseStatusRecyclerView()

    }
    override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }
        if(player!=null){
            player!!.stop()
        }
        super.onDestroy()
    }
    private fun setRestProgressBar()  {
        progressbar.progress=restProgress
        restTimer=object :CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished:Long) {
                restProgress++
                progressbar.progress=10-restProgress
                tvTimer.text=(10-restProgress).toString()
            }

            override fun onFinish(){
                progressbar.progress=0
                tvTimer.text="0"
               // Toast.makeText(this@ExerciseActivity,"Lets begin exercise !!",Toast.LENGTH_SHORT).show()
                llRestView.visibility=View.GONE
                llExerciseView.visibility=View.VISIBLE
                setupexerciseview()
            }
        }.start()


    }
    private fun setuprestview(){
        try{
            player=MediaPlayer.create(applicationContext,R.raw.press_start)
            player!!.isLooping=false
            player!!.start()
        }catch (e:Exception){
            e.printStackTrace()
        }

        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        exercisename.text=exerciseList!![currentExercisePosition+1].getName()
        llExerciseView.visibility=View.GONE
        llRestView.visibility=View.VISIBLE
        setRestProgressBar()

    }
    private fun setexerciseProgressBar(){
        currentExercisePosition++
        exerciseList!![currentExercisePosition].setisSelected(true)
        exerciseAdapter!!.notifyDataSetChanged()
        progressbarexercise.progress=exerciseProgress
        exerciseTimer=object :CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished:Long) {
                exerciseProgress++
                progressbarexercise.progress = 30 - exerciseProgress
                tvexercise.text = (30 - exerciseProgress).toString()
            }
            override fun onFinish(){
                exerciseList!![currentExercisePosition].setisCompleted(true)
                exerciseList!![currentExercisePosition].setisSelected(false)
                exerciseAdapter!!.notifyDataSetChanged()
                Toast.makeText(this@ExerciseActivity,"Rest time !!",Toast.LENGTH_SHORT).show()
                if(currentExercisePosition!=11)
                {
                    setuprestview()}
                else{
                    addingDate()
                    val i= Intent(this@ExerciseActivity,Finished_exercise::class.java)
                    startActivity(i)
                }
            }
        }.start()
    }
    private fun setupexerciseview(){
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }
        setexerciseProgressBar()
        tvexercisename.text=exerciseList!![currentExercisePosition].getName()
        ivImage.setImageResource(exerciseList!![currentExercisePosition].getimage())
    }
    private fun setupExerciseStatusRecyclerView(){
        rvexerciseStatus.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter= ExerciseStatusAdapter(exerciseList!!,this)
        rvexerciseStatus.adapter=exerciseAdapter
    }
    private fun customback(){
        val dialog =Dialog(this)
        dialog.setContentView(R.layout.dialog_box)
        dialog.Yes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.No.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun addingDate(){
        val calendar=Calendar.getInstance()
        val dateTime=calendar.time
        val sdf= SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.i("date : ",date)
        val dbHandler=SQLiteopenhelper(this,null)
        dbHandler.addDate(date)
    }
}