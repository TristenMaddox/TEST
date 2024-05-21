package com.example.test

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.test.databinding.ActivityMainBinding

class MainActivity  : AppCompatActivity() {
    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)

    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        if (fullBoard())
        {
         result("Draw")
        }

    }
    private fun result(title: String)
    {
        AlertDialog.Builder(context:this)
        .setTitle(title)
        .setPositiveButton("Reset")
        {_,_ ->
            resetBoard()
        }
    }
    private fun fullBoard(): Boolean
    {
        for (button in boardList)
        {
            if(button.text == "")
                return false
    }
        return true
}
    private fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if (currentTurn == Turn.NOUGHT)
        {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }
         else if (currentTurn == Turn.CROSS)
        {
            button.text = CROSS
            currentTurn = Turn.NOUGHT

        }
        setTurnLabel()
    }

    private fun setTurnLabel()
    {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
       else if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $CROSS"

        binding.textTV.text = turnText
    }
    companion object
    {


const val NOUGHT = "O"
const val CROSS = "X"
}
}