package com.example.my_khatabook

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my_khatabook.databinding.ActivityOnboardingBinding

lateinit var onboardingBinding: ActivityOnboardingBinding
class Onboarding_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(onboardingBinding.root)

        onboardingBinding.btnGetStarted.setOnClickListener {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        }
    }
}