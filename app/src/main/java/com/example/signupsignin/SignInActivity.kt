package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.signupsignin.database.Account
import com.example.signupsignin.database.DBHelper

class SignInActivity : AppCompatActivity() {

    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var signInButton: Button

    lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //actionbar
        val actionbar = supportActionBar!!
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        usernameEditText = findViewById(R.id.edt_username_SignIn)
        passwordEditText = findViewById(R.id.edt_password_SignIn)
        signInButton = findViewById(R.id.btn_signIn_SignIn)

        //initialize DB
        databaseHelper = DBHelper(applicationContext)

        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if(checkFields()){
                val account = databaseHelper.getAccount(username, password)
                if(account != null){
                    val intent = Intent(this,AccountActivity::class.java)
                    intent.putExtra("account", Account(account.id,account.username,account.mobile,account.location, account.password))
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Incorrect username or password",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        if(usernameEditText.text.trim().isNotEmpty() && passwordEditText.text.trim().isNotEmpty()){
            return true
        }
        if(usernameEditText.text.trim().isEmpty()){
            Toast.makeText(this,"enter username",Toast.LENGTH_SHORT).show()
        }else if(passwordEditText.text.trim().isEmpty()){
            Toast.makeText(this,"enter password",Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}