package com.irempakyurek.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.irempakyurek.rickandmorty.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHost =supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNav,navHost.navController)

        binding.toolbarTitle = getString(R.string.app_name)
    }
}