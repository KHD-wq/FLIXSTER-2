package com.example.project1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R

var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId", "CutPasteId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextAnswer = findViewById<EditText>(R.id.EditTextAnswer)
        val button = findViewById<Button>(R.id.Button)
        val guess1 = findViewById<TextView>(R.id.Guess1)
        val guess2 = findViewById<TextView>(R.id.Guess2)
        val guess3 = findViewById<TextView>(R.id.Guess3)
        val answerProgress = findViewById<TextView>(R.id.AnswerProgress)
        val answerProgress2 = findViewById<TextView>(R.id.AnswerProgress2)
        val answerProgress3 = findViewById<TextView>(R.id.AnswerProgress3)
        val final = findViewById<TextView>(R.id.Final)
        val guesses = findViewById<TextView>(R.id.Guesses)
        while (guesses.text.toString() == "Guesses") {
            guesses.text = "Guesses: 3"
            button.setOnClickListener {
                val answer = editTextAnswer.text.toString()
                if (answer == wordToGuess) {
                    answerProgress.text = checkGuess(answer)
                    final.text = wordToGuess

                } else {
                    guess1.text = editTextAnswer.text.toString()
                    answerProgress.text = final.text
                    answerProgress.text = checkGuess(answer)
                    guesses.text = "Guesses: 2"
                if (guesses.text.toString() == "Guesses: 2"){
                    button.setOnClickListener{
                        val answer2 = editTextAnswer.text.toString()
                        if (answer2 == wordToGuess){
                            guess2.text = editTextAnswer.text.toString()
                            answerProgress2.text = checkGuess(answer2)
                            final.text = wordToGuess
                        }else{
                            guess2.text = editTextAnswer.text.toString()
                            answerProgress2.text = checkGuess(answer2)
                            guesses.text = "Guesses: 1"
                        }
                        if (guesses.text.toString() == "Guesses: 1"){
                            button.setOnClickListener{
                                val answer3 = editTextAnswer.text.toString()
                                if (answer3 == wordToGuess){
                                    guess3.text = editTextAnswer.text.toString()
                                    answerProgress3.text = checkGuess(answer3)
                                    final.text = wordToGuess
                                }else{
                                    guess3.text = editTextAnswer.text.toString()
                                    answerProgress3.text = checkGuess(answer3)
                                    guesses.text = "Guesses: 0"
                                }
                                if (guesses.text == "Guesses: 0"){
                                    final.text = wordToGuess

                                }
                                button.text = "                     Reset"
                                button.setOnClickListener{

                                }
                            }
                        }
                    }
                }

                }
            }
        }



    }
}
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }

