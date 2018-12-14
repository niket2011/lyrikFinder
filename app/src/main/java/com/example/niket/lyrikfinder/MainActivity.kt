package com.example.niket.lyrikfinder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var editTextName: EditText
    lateinit var editTextlyrics: EditText
    lateinit var buttonSave:Button
    lateinit var buttonSearch:Button
    lateinit var buttonLogout:Button
    lateinit var ref:DatabaseReference
    val uid = FirebaseAuth.getInstance().uid ?: ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("songs")

        editTextName = findViewById(R.id.editTextName)
        editTextlyrics = findViewById(R.id.editTextlyrics)
        buttonSave = findViewById(R.id.buttonSave)
        buttonSearch = findViewById(R.id.buttonSearch)
        buttonLogout=findViewById(R.id.buttonLogout)
        buttonSave.setOnClickListener {
            saveHero()
        }
        buttonSearch!!.setOnClickListener {


            val intent = Intent(this@MainActivity, ViewActivity::class.java)
            println("Hi2")
            startActivity(intent)
        }
        buttonLogout.setOnClickListener {
            var fbAuth = FirebaseAuth.getInstance()
            fbAuth.addAuthStateListener {
                if (fbAuth.currentUser == null) {
                    this.finish()
                }

               // fbAuth.signOut()
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }



    }
     fun saveHero(){
        val name=editTextName.text.toString().trim()
        if(name.isEmpty()){
            editTextName.error="Please enter a name"
            return
        }

        val lyricname=editTextlyrics.text.toString().trim()
        if(lyricname.isEmpty()){
            editTextlyrics.error="Please enter the lyrics"
            return
        }


        val heroId=ref.push().key
        val hero=Hero(heroId!!,name,lyricname,"")

            ref.child(heroId).setValue(hero).addOnCompleteListener {
                Toast.makeText(applicationContext, "Lyrics saved", Toast.LENGTH_LONG).show()
            }

    }}



