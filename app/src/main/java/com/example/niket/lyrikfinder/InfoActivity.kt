package com.example.niket.lyrikfinder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val name = findViewById<TextView>(R.id.name)
        val lyrics = findViewById<TextView>(R.id.lyrics)
        name.setText(intent.getStringExtra("Name"))
        lyrics.setText(intent.getStringExtra("Lyrics"))
    }
}
