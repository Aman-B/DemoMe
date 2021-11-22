package com.example.demome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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

    override fun onBackPressed() {
        showDefaultDialog()
    }

    private fun showDefaultDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {

            setTitle(getString(R.string.quit_app_title))
            setMessage(getString(R.string.quit_app_message))
            setPositiveButton(getString(R.string.yes_quit_option)) { _, _ ->
                //end activity if the user says yes.
                finish()

            }
            setNegativeButton(getString(R.string.dont_quit_option)) { _, _ ->
                showLongToast("Glad you are staying! :)")
            }
        }.create().show()
    }

    private fun showLongToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}