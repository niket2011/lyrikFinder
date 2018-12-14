package com.example.niket.lyrikfinder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("Login", "Attempt login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful)
                            return@addOnCompleteListener
                        Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()

                        val intel = Intent(this, MainActivity::class.java)
                        startActivity(intel)
                        // else if successful

                           // Log.d("Main", "Successfully created user with uid: ${it.result.user.uid}")


                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
        }

        back_to_register_textview.setOnClickListener{
            //finish()
            val intel = Intent(this, RegisterActivity::class.java)
            startActivity(intel)


        }
    }

}