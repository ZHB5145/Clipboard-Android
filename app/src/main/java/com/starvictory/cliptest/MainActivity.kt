package com.starvictory.cliptest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.starvictory.cliptest.service.ClipboardService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent:Intent = Intent(this,ClipboardService::class.java)
        startService(intent)

    }
}