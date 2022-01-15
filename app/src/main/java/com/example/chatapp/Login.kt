package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class Login : AppCompatActivity() {
    private lateinit var edtmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtmail = findViewById(R.id.editEmail)
        edtpassword = findViewById(R.id.editPassword)
        btnlogin = findViewById(R.id.btnlogin)
        btnsignUp = findViewById(R.id.btnsignup)
        mAuth = FirebaseAuth.getInstance()

        btnsignUp.setOnClickListener {
        val intent = Intent(this,SignUp::class.java)
            startActivity(intent)

        }
        btnlogin.setOnClickListener {
            val email = edtmail.text.toString()
            val password = edtpassword.text.toString()

            login(email, password)
        }

    }
    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    Toast.makeText(this,"going to MainActivity",Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Email or password id wrong ,plz sign up if you did not",Toast.LENGTH_SHORT).show()
                }
            }

    }
}

    private fun login(email: String, password: String) {

    }

