package com.pjatk.myfinance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pjatk.myfinance.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityStartBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun onClick(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }


}