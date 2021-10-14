package com.example.margarettipizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.example.margarettipizza.utils.navigateTo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MargarettiPizza)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.main_container, MenuFragment::class.java, null, null)
//                .commit()
            navigateTo(MenuFragment.getInstance())
        }

    }
}