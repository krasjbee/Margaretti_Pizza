package com.example.margarettipizza.presentation.end

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.example.margarettipizza.utils.clearBackStack
import com.example.margarettipizza.utils.navigateTo

class EndFragment : Fragment(R.layout.fragment_end) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btToMenu: Button = view.findViewById(R.id.bt_to_menu)
        btToMenu.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.main_container, MenuFragment::class.java, null, null)
//                .commit()
            navigateTo(MenuFragment.getInstance())
            //clear backstack to prevent some weird flow and not get back after order is finished
            parentFragmentManager.clearBackStack()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun getInstance(): Fragment = EndFragment()
    }
}