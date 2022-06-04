package com.martian.aircraftwar.online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.martian.aircraftwar.R
import com.martian.aircraftwar.databinding.ActivityOnlineBinding

class OnlineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}