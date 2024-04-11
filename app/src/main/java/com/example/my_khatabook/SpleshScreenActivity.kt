package com.example.my_khatabook

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpleshScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh_screen)

        // Delayed execution of intent to navigate to next activity
        Handler().postDelayed({
            // Start your app's main activity
            val intent = Intent(this, Onboarding_Activity::class.java)
            startActivity(intent)

            // Close this activity
            finish()
        }, 3000)

    }
}