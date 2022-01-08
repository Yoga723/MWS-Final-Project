package com.example.mwsfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class MenuActivity : AppCompatActivity() {
    lateinit var menu1: ConstraintLayout
    lateinit var menu2: ConstraintLayout
    lateinit var menu3: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.hide()

        menu1 = findViewById(R.id.menu1)
        menu2 = findViewById(R.id.menu2)
        menu3 = findViewById(R.id.menu3)

        menu1.setOnClickListener{
            val intent = Intent()
        }

        menu2.setOnClickListener{}

        menu3.setOnClickListener{}
    }
}