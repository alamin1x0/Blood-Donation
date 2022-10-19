package com.developeralamin.bloodapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developeralamin.bloodapp.databinding.ActivityDeveloperBinding

class DeveloperActivity : AppCompatActivity() {

    lateinit var binding: ActivityDeveloperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.RLCompanyInfo2.setOnClickListener {
            val phone = "+8801762868864"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        binding.RLCompanyInfo1.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setAction(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("mailto:alaminsakib.cse@gmail.com"))
            intent.putExtra("subject", "Report or Suggestion | Learn With Al-Amin")
            startActivity(intent)
        }


        binding.RLCompanyInfo3.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer-alamin.web.app/"))
            startActivity(i)

        }
    }
}