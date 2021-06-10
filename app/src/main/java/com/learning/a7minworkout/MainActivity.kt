package com.learning.a7minworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    var tts:TextToSpeech?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tts= TextToSpeech(this,this)
        llstart.setOnClickListener {
            speakout("lets start our Seven minute workout ")
            while (tts!!.isSpeaking){}
            if(!tts!!.isSpeaking){
            val intent= Intent(this,ExerciseActivity::class.java)
            startActivity(intent)}
        }
        Bmi_start.setOnClickListener {
            val intent=Intent(this,bmi_Calculator::class.java)
            startActivity(intent)
        }
        History_start.setOnClickListener {
            val intent=Intent(this,History::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
    private fun speakout(txt:String){
        tts!!.speak(txt,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){

            val result=tts!!.setLanguage(Locale.ENGLISH)
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Here!!!",Toast.LENGTH_SHORT).show()
                Log.e("TTS","Language not found")
            }
        }
        else{
            Log.e("TTS","Initialization failed")
        }
    }
}