package com.martian.aircraftwar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.martian.aircraftwar.databinding.ActivityMainBinding
import com.martian.aircraftwar.online.Client

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread {
            Client.initConnection()
        }.start()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        this.intent = intent
        if (intent != null) {
            this.intent.putExtras(intent)
        }
    }

    override fun onDestroy() {
        Client.closeSocket()
        super.onDestroy()
    }
}