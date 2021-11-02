package com.example.margarettipizza

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.databinding.ActivityMainBinding
import com.example.margarettipizza.presentation.menu.MenuFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MargarettiPizza)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MenuFragment::class.java, null, null)
                .commit()
        }

    }
}