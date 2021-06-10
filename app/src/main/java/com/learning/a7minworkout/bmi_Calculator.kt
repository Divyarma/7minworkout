package com.learning.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_bmi__calculator.*
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbar_exercise_activity
import kotlinx.android.synthetic.main.activity_finished_exercise.*
import org.xml.sax.helpers.ParserAdapter

class bmi_Calculator : AppCompatActivity() {
    val Mview ="Metric units view"
    val USview ="US units view"
    var currectvisi=Mview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi__calculator)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar= supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setSystem()
        metric.setOnClickListener {
            setSystem()
        }
        US.setOnClickListener {
            setSystem()
        }
        calc.setOnClickListener {
            val BMI:Float
            if(metric.isChecked){
                val w=weight_intake.text.toString().toFloat()
                val h=height_intake.text.toString().toFloat()
                 BMI= ((w*100*100)/(h*h))
            }
            else{
                val w=weight_intakeUS.text.toString().toFloat()
                val f=feet_intake.text.toString().toFloat()
                var i=inch_intake.text.toString().toFloat()
                Log.d("input","i : $i  f : $f")
                i += (12*f)
                Log.d("input","i : $i ")
                BMI=((w/(i*i))*(703))
                Log.d("input","bmi : ${(w/(i*i)*703)} ")
            }
            val res:String
            bmitxt1.visibility=View.VISIBLE
            if(BMI>25){
                res="You are OverWeight \n start exercising   BMI : $BMI"
            }
            else if(BMI>18.5){
                res="Congratulations !!! Your BMI is good\n Keep it up   BMI : $BMI"
            }
            else{
                res="You are under weight\n Start a healthy diet   BMI : $BMI"
            }
            resultbmi.text=res
            resultbmi.visibility=View.VISIBLE
        }
    }
    private fun setSystem(){
        if(metric.isChecked){
            Metricll.visibility=View.VISIBLE
            USll.visibility=View.GONE
            weight_intake.getText()?.clear()
            height_intake.getText()?.clear()
        }
        else{
            USll.visibility=View.VISIBLE
            Metricll.visibility=View.GONE
            weight_intakeUS.getText()?.clear()
            feet_intake.getText()?.clear()
            inch_intake.getText()?.clear()
        }
    }
}