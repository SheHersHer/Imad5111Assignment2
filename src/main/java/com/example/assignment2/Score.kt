package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Score : AppCompatActivity() {
    private var isFeedbackVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)
        val feedback = findViewById<TextView>(R.id.feedBack)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")!!
        val answers = intent.getBooleanArrayExtra("answers")!!
        val userAnswers = intent.getBooleanArrayExtra("userAnswers")!!

        scoreDisplay.text = "Your score: $score / ${questions.size}"

        // set feeback text, but keep it initially hidden
        feedback.text = if (score >= 3) {
            "Great job!"
        } else {
            "Keep practising!"
        }
        feedback.visibility = View.GONE
        reviewButton.setOnClickListener {
            isFeedbackVisible = !isFeedbackVisible
            feedback.visibility = if (isFeedbackVisible) View.VISIBLE else View.GONE
            reviewButton.text = if (isFeedbackVisible) "Hide Feedback" else "Show Feedback"
            Toast.makeText(this, if (isFeedbackVisible) "Feedback Shown" else "Feedback Hidden", Toast.LENGTH_SHORT).show()

            val detailedFeedback = StringBuilder()

            for (i in questions.indices) {
                val status = if (userAnswers[i] == answers[i]) "Correct" else "Incorrect"
                detailedFeedback.append("${i + 1}. ${questions[i]} \n→ Your Answer: ${if (userAnswers[i]) "True" else "False"} — $status\n\n")
            }

            feedback.text = detailedFeedback.toString()

        }

        exitButton.setOnClickListener {
            finishAffinity() // Closes all activities
        }
    }
}