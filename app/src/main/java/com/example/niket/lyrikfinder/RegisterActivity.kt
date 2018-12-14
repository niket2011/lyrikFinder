package com.example.niket.lyrikfinder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.niket.lyrikfinder.LoginActivity
import com.example.niket.lyrikfinder.MainActivity
import com.example.niket.lyrikfinder.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {
            performRegister()

        }

        already_have_account_text_view.setOnClickListener {
            //Log.d("RegisterActivity", "Try to show login activity")

            // launch the login activity somehow

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performRegister() {
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email/password field cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterActivity", "Email is: " + email)
        Log.d("RegisterActivity", "Password: $password")

        // Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)


                .addOnCompleteListener {
                    if (!it.isSuccessful)
                        return@addOnCompleteListener

                    // else if successful

                    Log.d("Main", "Successfully created user with uid: ${it.result!!.user.uid}")
                    Toast.makeText(this, "Successfully created user", Toast.LENGTH_SHORT).show()
                    saveUserToFirebaseDatabase()


                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }
                .addOnFailureListener{
                    Log.d("Main", "Failed to create user: ${it.message}")
                    Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }


    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("users/$uid")

        val user = User(uid, username_edittext_register.text.toString(), email_edittext_register.text.toString())

        ref.setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"User saved sucessfully to database",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    //Log.d(TAG, "Failed to set value to database: ${it.message}")
                }
    }
}

class User(val uid: String, val username: String, val email:String)
