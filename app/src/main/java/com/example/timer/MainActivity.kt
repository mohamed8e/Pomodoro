package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    lateinit var pb : ProgressBar
    var isTimerRunning = false
    lateinit var time : CountDownTimer
    val start_time_inmili :Long = 25*60*1000
    var remainingTime : Long = start_time_inmili
    lateinit var text1 : TextView
    lateinit var text2 : TextView
    lateinit var text3 : TextView
    lateinit var startButon : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    text1 = findViewById(R.id.textView)
    text2 = findViewById(R.id.textView2)
    text3 = findViewById(R.id.textView3)
    startButon = findViewById(R.id.buton)
        pb = findViewById(R.id.progressBar)

        startButon.setOnClickListener {
            if (!isTimerRunning) {
                restartTimer()
            }
        }

        text3.setOnClickListener {
            resetTimer()
        }
    }

    private fun restartTimer() {
         time = object : CountDownTimer(start_time_inmili, 1*1000) {
            override fun onTick(timeleft: Long) {
                remainingTime = timeleft
                updattimeText()
                text1.text = "Keep going..."
                pb.progress = remainingTime.toDouble().div(start_time_inmili.toDouble()).times(100).toInt()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "finish ", Toast.LENGTH_SHORT).show()
                !isTimerRunning
            }
        }.start()
        isTimerRunning= true
    }
    private  fun resetTimer(){
        time.cancel()
        remainingTime=start_time_inmili
        updattimeText()
        text1.text = "Take pomodoro"
        !isTimerRunning
        pb.progress = 100
    }
    private  fun updattimeText(){
        val minute = remainingTime.div(1000).div(60)
        val second = remainingTime.div(1000) % 60
        val formatTime = String.format("%02d:%02d" , minute, second)
        text2.text=formatTime
    }
}


