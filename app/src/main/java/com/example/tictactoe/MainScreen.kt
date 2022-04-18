package com.example.tictactoe

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val actionBar = supportActionBar
        actionBar!!.title = "Choose game mode"

        val secondActivityBtn1: Button = findViewById (R.id.button_pvp)
        secondActivityBtn1.setOnClickListener {
            val intent1 = Intent(this, PlayerVsPlayer::class.java)
            startActivity(intent1)
        }
        val secondActivityBtn2: Button = findViewById (R.id.button_pvc)
        secondActivityBtn2.setOnClickListener {
            val intent2 = Intent(this, PlayerVsComputer::class.java)
            startActivity(intent2)
        }
    }
}
