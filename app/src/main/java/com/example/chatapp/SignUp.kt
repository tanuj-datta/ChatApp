package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        edtName = findViewById(R.id.edtName)
        edtmail = findViewById(R.id.edtmail)
        edtpassword = findViewById(R.id.edtPassword)
        btnsignup = findViewById(R.id.btnsignup)
        mAuth = FirebaseAuth.getInstance()

        btnsignup.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtmail.text.toString()
            val password = edtpassword.text.toString()

            signUp(name,email, password)

        }


    }

    private fun signUp(name:String, email: String, password: String) {
        //logic for signing in user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //code for jumping to home activity
                        addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error Occured",Toast.LENGTH_SHORT).show()
                }
            }


    }
    private fun addUserToDatabase(name:String,email: String,uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}

