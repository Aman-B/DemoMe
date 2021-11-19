package com.example.demome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.tutorialActivity_btn).setOnClickListener(View.OnClickListener { startTutorialActivity() })
        findViewById<Button>(R.id.loginActivity_btn).setOnClickListener(View.OnClickListener { startHomeActivity() })
    }

    private fun startTutorialActivity() {

        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    }
}