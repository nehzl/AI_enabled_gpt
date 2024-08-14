package com.example.ai_application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etprompt = findViewById<EditText>(R.id.ET_prompt)
        val btnGen = findViewById<Button>(R.id.btnSubmit)
        val result = findViewById<TextView>(R.id.TvResult)

        btnGen.setOnClickListener {
            val prompt = etprompt.text.toString() //getting the prompt
            val generativeModel = GenerativeModel(//create variable
                modelName = "gemini-1.5-flash", //give model name & api key
                apiKey = "AIzaSyCYeUD6PhkxIHOAp8kCAc7FZe_-Xlr_IQM"
            )
            runBlocking {
                val response = generativeModel.generateContent(prompt)
                result.text = response.text
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}