package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlin.random.Random
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var calculationResult:Int = 0
    private var question:Int = 1
    private var level:Int = 1
    private var score:Int = 0
    private var bad:Int = 0
    private var good:Int = 0
    private var pts:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculPts()
        generateTexte()
        generateBoard()
        generateCalculation()
        goodAnswer()
        badAnswer()


        findViewById<TextView>(R.id.levelOne).setOnClickListener {
            if( question > 10) {
                return@setOnClickListener
            }

            level = 1
            calculPts()
            generateBoard()
            generateCalculation()
        }

        findViewById<TextView>(R.id.levelTwo).setOnClickListener {
            if( question > 10) {
                return@setOnClickListener
            }

            level = 2
            calculPts()
            generateBoard()
            generateCalculation()
        }

        findViewById<TextView>(R.id.levelThree).setOnClickListener {
            if( question > 10) {
                return@setOnClickListener
            }

            level = 3
            calculPts()
            generateBoard()
            generateCalculation()

        }

        findViewById<TextView>(R.id.replay).setOnClickListener {
            question = 1
            score = 0
            bad = 0
            good = 0
            pts = 0

            goodAnswer()
            badAnswer()

            calculPts()
            generateTexte()
            generateBoard()
            generateCalculation()
        }

    }

    private fun generateTexte() {

        findViewById<TextView>(R.id.validateButton).setOnClickListener {
            if( question > 10) {
                return@setOnClickListener
            }
            question++

            if( findViewById<TextView>(R.id.answer).text.toString() == calculationResult.toString()) {

                findViewById<TextView>(R.id.answer).text = ""
                good++
                score += pts

                generateBoard()
                generateCalculation()

                Toast.makeText( this,  "Bonne réponse !", Toast.LENGTH_SHORT).show()

                goodAnswer()

                quizzFini()

            } else {
                bad++
                generateBoard()

                Toast.makeText( this,  "Mauvaise réponse !", Toast.LENGTH_SHORT).show()

                badAnswer()

                quizzFini()
            }
        }
    }

    private fun generateCalculation() {

        if ( level == 1) {
            val firstNumber: Int = Random.nextInt(1, 10)
            val secondNumber: Int = Random.nextInt(1, 10)
            calculationResult = firstNumber + secondNumber
            findViewById<TextView>(R.id.mainTitle).text = "$firstNumber + $secondNumber"

        } else if (level == 2) {
            val firstNumber: Int = Random.nextInt(10, 100)
            val secondNumber: Int = Random.nextInt(10, 100)
            calculationResult = firstNumber + secondNumber
            findViewById<TextView>(R.id.mainTitle).text = "$firstNumber + $secondNumber"

        } else if (level == 3) {
            val firstNumber: Int = Random.nextInt(100, 1000)
            val secondNumber: Int = Random.nextInt(100, 1000)
            calculationResult = firstNumber + secondNumber
            findViewById<TextView>(R.id.mainTitle).text = "$firstNumber + $secondNumber"
        }
    }

    private fun generateBoard() {
        findViewById<TextView>(R.id.nbQuestion).text = "Question $question/10"
        findViewById<TextView>(R.id.Level).text = "Niveau $level ($pts points)"
        findViewById<TextView>(R.id.Score).text = "$score points"

    }

    private fun calculPts() {
        if(level == 1){
            pts = 100

        } else if(level == 2){
            pts = 200

        } else if(level == 3){
            pts = 300
        }
    }

    private fun quizzFini() {
        if( question > 10) {
            findViewById<TextView>(R.id.nbQuestion).text = "QUIZ FINI"
            findViewById<TextView>(R.id.mainTitle).text = ""
        }
    }

    private fun goodAnswer() {
        findViewById<TextView>(R.id.goodAnswer).text = "Bonne(s) réponse(s) : $good"
    }

    private fun badAnswer() {
        findViewById<TextView>(R.id.badAnswer).text = "Mauvaise(s) réponse(s) : $bad"
    }

}