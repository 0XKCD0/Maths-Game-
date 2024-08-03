package com.xyz.mathgamekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.Locale
import kotlin.random.Random
import kotlin.random.nextInt

class GameActivity : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var textAnswer : EditText

    lateinit var button0k : Button
    lateinit var buttonNext : Button

    var correctAnswer =  0
    var userScore = 0
    var userLife = 3


    lateinit var timer: CountDownTimer
    private val startTimeInMillis : Long = 30000
    var timeLeftInMillis : Long = startTimeInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "Addition"

        textScore = findViewById(R.id.textViewScoreValue)
        textLife = findViewById(R.id.textViewLifeValue)
        textTime = findViewById(R.id.textViewTimeValue)
        textQuestion = findViewById(R.id.textViewQuestion)
        textAnswer = findViewById(R.id.editTextAnswer)
        button0k = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()


        button0k.setOnClickListener {
            val input = textAnswer.text.toString()

            if (input == ""){
                Toast.makeText(applicationContext, "Please enter your answer", Toast.LENGTH_LONG).show()
                buttonNext.isVisible = false

            }
            else
            {
                pauseTimer()
                buttonNext.isVisible = true
               val useranswer = input.toInt()

                if (useranswer == correctAnswer){
                    userScore = userScore + 10
                    textQuestion.text = "Congratulations, your answer is correct."
                    textScore.text =  userScore.toString()
                }
                else{
                    userLife--
                    textQuestion.text = "Sorry, your answer is wrong."
                    textLife.text = userLife.toString()
                }
            }

        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            textAnswer.setText("")

            buttonNext.isVisible = false
            if (userLife==0){
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameContinue()

            }
        }
    }

    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)


        textQuestion.text=("$number1 + $number2")

        correctAnswer = number1 + number2
//        val answer = input.toInt()

//        if (noAnswer == null){
//            buttonNext.isVisible = false
//
//        }

        startTimer()

    }

    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMillis, 1000){

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()

            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sorry, your time is up!"

            }

        }.start()

    }

    fun updateText(){
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(), "%02d", remainingTime)

    }

    fun pauseTimer(){
        timer.cancel()

    }

    fun resetTimer(){
        timeLeftInMillis = startTimeInMillis
        updateText()
    }
}