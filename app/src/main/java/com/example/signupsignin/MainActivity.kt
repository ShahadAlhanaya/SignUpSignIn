package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var signInCard : CardView
    lateinit var signUpCard : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInCard = findViewById(R.id.card_signIn)
        signUpCard = findViewById(R.id.card_signUp)

        signInCard.setOnClickListener{
            startActivity(Intent(this,SignInActivity::class.java))
        }
        signUpCard.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}