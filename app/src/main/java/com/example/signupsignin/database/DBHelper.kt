package com.example.signupsignin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "accounts.db"
        private const val TABLE_ACCOUNTS = "Accounts"

        private const val KEY_ID = "ID"
        private const val KEY_USERNAME = "Username"
        private const val KEY_MOBILE = "Mobile"
        private const val KEY_LOCATION = "Location"
        private const val KEY_PASSWORD = "Password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_ACCOUNTS ($KEY_ID INTEGER PRIMARY KEY ,$KEY_USERNAME TEXT ,$KEY_MOBILE TEXT ,$KEY_LOCATION TEXT ,$KEY_PASSWORD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ACCOUNTS")
        onCreate(db)
    }

    fun createAccount(username: String, mobile: String, location: String, password: String): Long {
        val sqLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_USERNAME, username)
        contentValues.put(KEY_MOBILE, mobile)
        contentValues.put(KEY_LOCATION, location)
        contentValues.put(KEY_PASSWORD, password)
        val success = sqLiteDatabase.insert(TABLE_ACCOUNTS,null, contentValues)
        sqLiteDatabase.close()
        return success
    }

    fun getAccount(username: String, password: String): Account? {
        val account : Account
        val sqLiteDatabase = writableDatabase
        val cursor : Cursor = sqLiteDatabase.query(
            TABLE_ACCOUNTS, null,"LOWER($KEY_USERNAME)=?", arrayOf(username.lowercase()),null,null,null)
        if (cursor.moveToFirst()) {
            val savedPassword = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            if(password == savedPassword){
                val savedId = cursor.getString(cursor.getColumnIndex(KEY_ID))
                val savedUsername = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
                val savedMobile = cursor.getString(cursor.getColumnIndex(KEY_MOBILE))
                val savedLocation = cursor.getString(cursor.getColumnIndex(KEY_LOCATION))
                account = Account(savedId.toLong(), savedUsername, savedMobile,savedLocation,savedPassword)
                return account
            }
        }
        return null
    }

    fun checkUsernameExist(username: String): Boolean {
        val sqLiteDatabase = writableDatabase
        val cursor : Cursor = sqLiteDatabase.query(TABLE_ACCOUNTS, null,"LOWER($KEY_USERNAME)=?", arrayOf(username.lowercase()),null,null,null)
        if (cursor.moveToFirst()) {
            cursor.close()
            return true
        }
        cursor.close()
        return false
    }
}