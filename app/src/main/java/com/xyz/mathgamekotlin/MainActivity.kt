package com.xyz.mathgamekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var Addition : Button
    lateinit var Subtraction : Button
    lateinit var Multi : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Addition = findViewById(R.id.buttonAdd)
        Subtraction = findViewById(R.id.buttonSub)
        Multi = findViewById(R.id.buttonMul)


        Addition.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
    }
}