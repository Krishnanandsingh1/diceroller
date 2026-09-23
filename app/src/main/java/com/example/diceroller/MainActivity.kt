package com.example.diceroller

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage: ImageView
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        diceImage = findViewById(R.id.dice_image)

        //  Sound initialize
        mediaPlayer = MediaPlayer.create(this, R.raw.dice_sound)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {

        //  Play sound
        mediaPlayer.start()

        //  Animation
        diceImage.animate().rotationBy(360f).setDuration(300).start()

        //  Random number
        val randomInt = Random.nextInt(6) + 1

        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // ⏱ Delay thoda realistic feel ke liye
        diceImage.postDelayed({
            diceImage.setImageResource(drawableResource)
        }, 300)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}