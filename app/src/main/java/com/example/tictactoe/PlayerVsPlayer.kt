package com.example.tictactoe

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

@RequiresApi(Build.VERSION_CODES.M)
class PlayerVsPlayer : AppCompatActivity(), View.OnClickListener {
    private lateinit var b0 : Button
    private lateinit var b1 : Button
    private lateinit var b2 : Button
    private lateinit var b3 : Button
    private lateinit var b4 : Button
    private lateinit var b5 : Button
    private lateinit var b6 : Button
    private lateinit var b7 : Button
    private lateinit var b8 : Button
    private lateinit var tv : TextView
    private var player1 = 0
    private var player2 = 1
    private var activePlayer = player1
    private lateinit var filledPos : IntArray
    private var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pvp)

        val actionBar = supportActionBar
        actionBar!!.title = "Player vs Player"
        actionBar.setDisplayHomeAsUpEnabled(true)

        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        tv = findViewById(R.id.textView2)
        b0 = findViewById(R.id.b0)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)

        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(!gameActive)
            return
        val btnClicked = findViewById<Button>(v!!.id)
        val clickedTag = Integer.parseInt(btnClicked.tag.toString())

        if(filledPos[clickedTag]!=-1)
            return

        filledPos[clickedTag] = activePlayer

        if(activePlayer == player1){
            btnClicked.text = "0"
            activePlayer = player2
            tv.text = "Player 2 Turn"
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.Color4)
        }
        else{
            btnClicked.text = "X"
            activePlayer = player1
            tv.text = "Player 1 Turn"
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.Color5)
        }
        checkForWin()
    }

    private fun checkForWin() {
        val winPos = arrayOf(intArrayOf(0,1,2),intArrayOf(3,4,5),intArrayOf(6,7,8),
            intArrayOf(0,3,6),intArrayOf(1,4,7),intArrayOf(2,5,8),
            intArrayOf(0,4,8),intArrayOf(2,4,6))

        for(i in winPos.indices){
            val val0 = winPos[i][0]
            val val1 = winPos[i][1]
            val val2 = winPos[i][2]

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if(filledPos[val0]!=-1){
                    gameActive = false
                    if(filledPos[val0] == player1){
                        showMessage("Player 1 is the Winner")
                    }else{
                        showMessage("Player 2 is the Winner")
                    }
                    return
                }
            }
        }
        var count = 0
        for(element in filledPos){
            if(element ==-1){
                count++
            }
        }
        if(count==0){
            showMessage("It's a Draw")
            return
        }
    }
    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart Game") { _, _ -> restartGame() }
            .show()
    }
    private fun restartGame() {
        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer = player1
        gameActive = true
        tv.text = "Player 1 Turn"
        b0.text = ""
        b1.text = ""
        b2.text = ""
        b3.text = ""
        b4.text = ""
        b5.text = ""
        b6.text = ""
        b7.text = ""
        b8.text = ""
        b0.backgroundTintList = getColorStateList(R.color.Color2)
        b1.backgroundTintList = getColorStateList(R.color.Color2)
        b2.backgroundTintList = getColorStateList(R.color.Color2)
        b3.backgroundTintList = getColorStateList(R.color.Color2)
        b4.backgroundTintList = getColorStateList(R.color.Color2)
        b5.backgroundTintList = getColorStateList(R.color.Color2)
        b6.backgroundTintList = getColorStateList(R.color.Color2)
        b7.backgroundTintList = getColorStateList(R.color.Color2)
        b8.backgroundTintList = getColorStateList(R.color.Color2)
    }
}