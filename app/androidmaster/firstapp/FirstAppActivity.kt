package com.example.androidmaster.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)                 //Access the button component by its id
        val etName = findViewById<AppCompatEditText>(R.id.etName)                   //Access the editText component by its id

        fun saveUserName(name: String) {                                                      // For saving the name entered by the user, so we can use it on another app
            val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)//We create a sharedPreferences object
            with(sharedPreferences.edit()) {                                              //We create a sharedPreferences editor object
                putString("userName", name)                                               //We add a string to the object
                apply()     //We apply the changes we've done to the object
            }
        }

        btnStart.setOnClickListener {                                               //Is the button clicked?
            val name = etName.text.toString()                                       //We convert the value of the editText component to String

            if(name.isNotEmpty()){   //We check if the name is not empty
                saveUserName(name)
                val intent = Intent(this, ResultActivity::class.java) //We create an intent to go to the ResultActivity
                intent.putExtra("EXTRA_NAME", name)                           //We add the name to the intent
                startActivity(intent)                                               //We start the activity
            }
        }
    }
}