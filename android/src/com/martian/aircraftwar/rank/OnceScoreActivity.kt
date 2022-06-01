package com.martian.aircraftwar.rank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.martian.aircraftwar.databinding.ActivityOnceScoreBinding

class OnceScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnceScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnceScoreBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}