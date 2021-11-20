package com.example.view.end

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.view.R
import com.example.view.databinding.FragmentEndBinding
import com.example.view.menu.MenuFragment
import com.example.view.utils.clearAllBackStack

class EndFragment : Fragment(R.layout.fragment_end) {

    private val binding by viewBinding(FragmentEndBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            llClickable.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MenuFragment())
                    .commit()
                //clear backstack to prevent some weird flow and not get back after order is finished
                parentFragmentManager.clearAllBackStack()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}