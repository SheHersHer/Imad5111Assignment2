package com.example.assignment2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Questions : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionsText: TextView
    private lateinit var nextButton: Button
    private lateinit var feedBack: TextView

    private val questions = arrayOf(
        "Nelson Mandela was the first president in SA." ,
        "Pretoria is the capital city of South africa " ,
        "The planet earth is flat" ,
        "There are 365 days in a leap year." ,
        "The currency of japan is Yen." ,
        "Sound travels faster than light." // false
    )
    private val answers = arrayOf(true , true , false , false , true , false)
    private val userAnswers = BooleanArray(questions.size)


    private var currentIndex = 0
    private var score = 0
    private var answered = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        questionsText = findViewById(R.id.questionText)
        nextButton = findViewById(R.id.nextButton)
        feedBack = findViewById(R.id.feedbackView)

        loadQuestion()

        trueButton.setOnClickListener {
            if (!answered) checkAnswer(true)
        }

        falseButton.setOnClickListener {
            if (!answered) checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                val intent = Intent(this , Score::class.java)
                intent.putExtra("score" , score)
                intent.putExtra("total" , questions.size)
                intent.putExtra("questions" , questions)
                intent.putExtra("answers" , answers.toBooleanArray())
                intent.putExtra("userAnswers", userAnswers)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        questionsText.text = questions[currentIndex]
        feedBack.text = ""
        answered = false
    }

    private fun checkAnswer(userAnswer: Boolean) {
        userAnswers[currentIndex] = userAnswer

        if (userAnswer == answers[currentIndex]) {
            Toast.makeText(this , "Correct!" , Toast.LENGTH_SHORT).show()
            feedBack.text = "Correct"
            score++
        } else {
            Toast.makeText(this , "Incorrect!" , Toast.LENGTH_SHORT).show()
            feedBack.text = "Incorrect"
        }
        answered = true



    }
}
