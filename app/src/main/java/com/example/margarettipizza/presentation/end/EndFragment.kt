package com.example.margarettipizza.presentation.end

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.menu.MenuFragment

class EndFragment : Fragment(R.layout.fragment_end) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btToMenu = view.findViewById<Button>(R.id.bt_to_menu)
        btToMenu.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, MenuFragment::class.java, null, null)
                .commit()

            for (i in 0..parentFragmentManager.backStackEntryCount) {
                parentFragmentManager.popBackStack()
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }
}