package com.example.quicksellandroidtakehomeassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quicksellandroidtakehomeassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.quickSellChatNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}