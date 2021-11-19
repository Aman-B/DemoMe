package com.example.demome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class TutorialActivity : AppCompatActivity() {

    private lateinit var tutorialImageChangeButton: Button
    private var imageIndex: Int =0
    private lateinit var tutorialIV: ImageView

    private val imageFileNames = arrayOf("tutorial1","tutorial2","tutorial3","tutorial4","tutorial5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        tutorialIV = findViewById<ImageView>(R.id.tutorial_IV)
        tutorialImageChangeButton = findViewById<Button>(R.id.tutorialImageChange_btn)

        changeTutorialImage()

        tutorialImageChangeButton.setOnClickListener(View.OnClickListener {
            imageIndex++

            if(imageIndex>4)
            {
                finish()
            }
            else{
                if(imageIndex==4)
                    tutorialImageChangeButton.setText("Ok, got it!")
                changeTutorialImage()
            }

        })

    }

    private fun changeTutorialImage(){
        Glide
            .with(this)
            .load(getResources()
                .getIdentifier(imageFileNames.get(imageIndex), "mipmap", this.getPackageName()))
            .centerCrop()
            .into(tutorialIV)
    }
}