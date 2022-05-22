package com.martian.aircraftwar.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.martian.aircraftwar.R
import com.martian.aircraftwar.databinding.ActivityShopBinding


class ShopActivity : AppCompatActivity() {


    private lateinit var binding: ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}