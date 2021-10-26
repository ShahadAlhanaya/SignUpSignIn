package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.signupsignin.database.Account

class AccountActivity : AppCompatActivity() {

    lateinit var usernameEditText: EditText
    lateinit var mobileEditText: EditText
    lateinit var locationEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var signOutButton: Button

    lateinit var account : Account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        usernameEditText = findViewById(R.id.edt_username_Account)
        mobileEditText = findViewById(R.id.edt_mobile_Account)
        locationEditText = findViewById(R.id.edt_location_Account)
        passwordEditText = findViewById(R.id.edt_password_Account)
        signOutButton = findViewById(R.id.btn_signOut_Account)

        if(intent.hasExtra("account")){
            account = intent.extras?.get("account") as Account
            usernameEditText.setText(account.username)
            mobileEditText.setText(account.mobile)
            locationEditText.setText(account.location)
            passwordEditText.setText(account.password)
        }

        signOutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_DOCUMENT  or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}