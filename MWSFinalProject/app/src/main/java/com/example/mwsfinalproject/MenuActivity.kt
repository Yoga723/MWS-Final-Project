package com.example.mwsfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mwsfinalproject.Package_Burger.ListBurger
import com.example.mwsfinalproject.Package_Pizza.ListPizza

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
            val intent = Intent(this, ListBurger::class.java)
            startActivity(intent)
        }

        menu2.setOnClickListener{
            val intent = Intent(this, ListPizza::class.java)
            startActivity(intent)
        }

        menu3.setOnClickListener{}
    }
}