package com.example.tictactoe

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.M)
class PlayerVsComputer : AppCompatActivity() {
    private lateinit var b0 : Button
    private lateinit var b1 : Button
    private lateinit var b2 : Button
    private lateinit var b3 : Button
    private lateinit var b4 : Button
    private lateinit var b5 : Button
    private lateinit var b6 : Button
    private lateinit var b7 : Button
    private lateinit var b8 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pvc)

        val actionBar = supportActionBar
        actionBar!!.title = "Player vs Computer"
        actionBar.setDisplayHomeAsUpEnabled(true)

        b0 = findViewById(R.id.b0)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)
    }
    fun buClick( view: View) {
        val buSelected = view as Button
        var cellId = 0
        when (buSelected.id) {
            R.id.b0 -> cellId = 1
            R.id.b1 -> cellId = 2
            R.id.b2 -> cellId = 3
            R.id.b3 -> cellId = 4
            R.id.b4 -> cellId = 5
            R.id.b5 -> cellId = 6
            R.id.b6 -> cellId = 7
            R.id.b7 -> cellId = 8
            R.id.b8 -> cellId = 9
        }
        playGame(cellId, buSelected)
    }
    private var activePlayer = 1
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var count = 0

    private fun playGame(cellId:Int, buSelected:Button) {
        if (activePlayer == 1 && count < 9) {
            buSelected.text = "X"
            buSelected.backgroundTintList = getColorStateList(R.color.Color4)
            buSelected.setTextColor(Color.BLACK)
            player1.add(cellId)
            activePlayer = 2
            count++
            autoPlay()
        } else if(activePlayer == 2 && count < 9) {
            buSelected.text = "0"
            buSelected.backgroundTintList = getColorStateList(R.color.Color5)
            buSelected.setTextColor(Color.BLACK)
            player2.add(cellId)
            activePlayer = 1
            count++
        }
        buSelected.isEnabled = false
        checkWinner()
    }
    private fun checkWinner() {
        var winner = -1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) { winner = 1 }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) { winner = 2 }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) { winner = 1 }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) { winner = 2 }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) { winner = 1 }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) { winner = 2 }

        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) { winner = 1 }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) { winner = 2 }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) { winner = 1 }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) { winner = 2 }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) { winner = 1 }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) { winner = 2 }

        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) { winner = 1 }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) { winner = 2 }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) { winner = 1 }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) { winner = 2 }

        when {
            winner == 1 -> {
                showMessage("You are the Winner")
            }
            winner == 2 -> {
                showMessage("Computer is the Winner")
            }
            count == 9 -> {
                showMessage("It's a Draw!")
            }
        }
    }
    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Return to main menu") { _, _ -> finish() }
            .show()
    }
    private fun autoPlay(){
        if (count < 9){
            val emptyCells = ArrayList<Int>()
            for(cellId in 1..9){
                if( !(player1.contains(cellId) || player2.contains(cellId))){
                    emptyCells.add(cellId)
                }
            }
            val r = Random()
            val randIndex = r.nextInt(emptyCells.size)
            val cellId = emptyCells[randIndex]
            val buSelected:Button = when(cellId){
                1-> b0
                2-> b1
                3-> b2
                4-> b3
                5-> b4
                6-> b5
                7-> b6
                8-> b7
                9-> b8
                else -> { b0}
            }
            playGame(cellId, buSelected)
        }
    }
}