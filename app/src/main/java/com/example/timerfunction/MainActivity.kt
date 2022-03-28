package com.example.timerfunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.timerfunction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private var countDownTimer:CountDownTimer?=null
    private var timeDuration:Long=60000
    private var pauseofset:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvTimer?.text="${(timeDuration/1000).toString()}"
        binding?.btnStart?.setOnClickListener(){
            startTimer(pauseofset)
        }
        binding?.btnpause?.setOnClickListener(){
            pasueTimer()
        }
        binding?.btnreset?.setOnClickListener(){
            resetTimer()
        }
    }
    private fun startTimer(pauseoffsetL:Long){

        countDownTimer= object : CountDownTimer
            (timeDuration-pauseoffsetL,1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseofset=timeDuration-millisUntilFinished
                binding?.tvTimer?.text=
                    (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,
                    "Timer is finished",Toast.LENGTH_SHORT).show()

            }

        }.start()
    }
        private fun pasueTimer(){
            if (countDownTimer!=null){
                countDownTimer!!.cancel()
            }
        }
    private fun resetTimer(){
        if (countDownTimer!=null){
            countDownTimer!!.cancel()
            binding?.tvTimer?.text="${(timeDuration/1000).toString()}"
        }
    }
}