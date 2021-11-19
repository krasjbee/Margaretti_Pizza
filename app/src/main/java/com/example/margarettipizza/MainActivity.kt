package com.example.margarettipizza

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.databinding.ActivityMainBinding
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.example.margarettipizza.presentation.preview.PreviewFragment
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
            //Change statusbar color according to fragment
            supportFragmentManager.addOnBackStackChangedListener {
                when (supportFragmentManager.fragments.last()) {
                    is PreviewFragment -> window.statusBarColor =
                        resources.getColor(R.color.black, null)
                    else -> window.statusBarColor =
                        resources.getColor(R.color.background_color, null)
                }
            }

            supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
                when (fragment) {
                    is PreviewFragment -> window.statusBarColor =
                        resources.getColor(R.color.black, null)
                    else -> window.statusBarColor =
                        resources.getColor(R.color.background_color, null)
                }
            }
        }
    }
}