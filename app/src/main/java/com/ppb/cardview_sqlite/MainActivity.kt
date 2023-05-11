package com.ppb.cardview_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.ppb.cardview_sqlite.connection.DBHelper

class MainActivity : AppCompatActivity() {
    
    lateinit var userDBHelper: DBHelper
    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var inputUsername: EditText
    lateinit var inputFullname: EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        inputEmail = findViewById(R.id.input_email)
        inputPassword = findViewById(R.id.input_password)
        inputUsername = findViewById(R.id.input_username)
        inputFullname = findViewById(R.id.input_nama)
        userDBHelper = DBHelper(this)
    }

    fun addData(view: View) {

        var emailIn = inputEmail.text.toString()
        var passwordIn = inputPassword.text.toString()
        var usernameIn = inputUsername.text.toString()
        var fullnameIn = inputFullname.text.toString()
        userDBHelper.insertData(emailIn, passwordIn, usernameIn, fullnameIn)
        inputEmail.setText("")
        inputPassword.setText("")
        inputUsername.setText("")
        inputFullname.setText("")
    }

    fun showAll(view: View) {

        var pindah = Intent(this, RVDBActivity::class.java)

        startActivity(pindah)
    }
}