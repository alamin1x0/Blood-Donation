package com.developeralamin.bloodapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developeralamin.bloodapp.R
import com.developeralamin.bloodapp.databinding.ActivityForgotBinding
import com.developeralamin.bloodapp.utils.Config
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgotBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.usergoBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            val email = binding.userEmail.text.toString()

            if (email.isEmpty()) {
                binding.userEmail.setError("Please Enter you Eamil")
                binding.userEmail.requestFocus()
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    Config.showDialog(this)
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Check your Email", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Config.hideDialog()
                        Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}