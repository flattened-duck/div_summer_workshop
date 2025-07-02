package com.yandex.divkit.sample.summer2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.divkit.sample.summer2025.databinding.ActivityMainPageBinding

class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
    }
}
