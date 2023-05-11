package com.ppb.cardview_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.ppb.cardview_sqlite.connection.DBHelper

class UpdateActivity : AppCompatActivity() {

    lateinit var userDBHelper: DBHelper
    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var inputUsername: EditText
    lateinit var inputFullname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        inputEmail = findViewById(R.id.input_emailku)
        inputPassword = findViewById(R.id.input_passwordku)
        inputUsername = findViewById(R.id.input_usernameku)
        inputFullname = findViewById(R.id.input_namaku)
        userDBHelper = DBHelper(this)
        val bundle = intent.extras

        if (bundle != null) {
            inputEmail.setText(bundle.getString("emailku"))
            inputUsername.setText(bundle.getString("usernameku"))
            inputPassword.setText(bundle.getString("passwordku"))
            inputFullname.setText(bundle.getString("fullnameku"))
        }
    }

    fun updateData(view: View) {

        var emailIn = inputEmail.text.toString()
        var passwordIn = inputPassword.text.toString()
        var usernameIn = inputUsername.text.toString()
        var fullnameIn = inputFullname.text.toString()
        userDBHelper.updateData(emailIn, passwordIn, usernameIn, fullnameIn)
        var pindah = Intent(this, RVDBActivity::class.java)

        startActivity(pindah)
    }

    fun cancelData(view: View) {

        var pindah = Intent(this, RVDBActivity::class.java)

        startActivity(pindah)
    }
}