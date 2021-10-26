package com.example.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.signupsignin.database.Account
import com.example.signupsignin.database.DBHelper

class SignUpActivity : AppCompatActivity() {

    lateinit var usernameEditText: EditText
    lateinit var mobileEditText: EditText
    lateinit var locationEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var signUpButton: Button

    lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //actionbar
        val actionbar = supportActionBar!!
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        usernameEditText = findViewById(R.id.edt_username_SignUp)
        mobileEditText = findViewById(R.id.edt_mobile_SignUp)
        locationEditText = findViewById(R.id.edt_location_SignUp)
        passwordEditText = findViewById(R.id.edt_password_SignUp)
        signUpButton = findViewById(R.id.btn_signUp_SignUp)

        //initialize DB
        databaseHelper = DBHelper(applicationContext)

        signUpButton.setOnClickListener { 
            if(checkFields()){
                val username = usernameEditText.text.toString()
                val mobile = mobileEditText.text.toString()
                val location = locationEditText.text.toString()
                val password = passwordEditText.text.toString()
                if(!databaseHelper.checkUsernameExist(username)){
                    val id = databaseHelper.createAccount(username,mobile,location,password)
                    if(id >= 0){
                        Toast.makeText(this,"Account created Successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,AccountActivity::class.java)
                        intent.putExtra("account", Account(id,username,mobile,location, password))
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Username already exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        if(checkUserName()){
            if(checkMobile()){
                if(checkLocation()){
                    if(checkPassword()){
                        return true
                    }else{
                        Toast.makeText(this,"enter an 8 characters password", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }else{
                    Toast.makeText(this,"enter a valid location", Toast.LENGTH_SHORT).show()
                    return false
                }
            }else{
                Toast.makeText(this,"enter a valid mobile number", Toast.LENGTH_SHORT).show()
                return false
            }
        }else{
            Toast.makeText(this,"enter a valid username", Toast.LENGTH_SHORT).show()
            return false
        }
        return false
    }

    private fun checkPassword(): Boolean {
        if(passwordEditText.text.trim().isNotEmpty()){
            return true
        }
        return false
    }

    private fun checkLocation(): Boolean {
        if(locationEditText.text.trim().isNotEmpty()){
            return true
        }
        return false
    }

    private fun checkMobile(): Boolean {
        if(mobileEditText.text.trim().isNotEmpty()){
            return true
        }
        return false
    }

    private fun checkUserName(): Boolean {
        if(usernameEditText.text.trim().isNotEmpty()){
            return true
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}