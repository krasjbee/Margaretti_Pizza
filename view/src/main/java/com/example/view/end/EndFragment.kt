package com.example.margarettipizza.presentation.end

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.view.R
import com.example.view.databinding.FragmentEndBinding

class EndFragment : Fragment(R.layout.fragment_end) {

    private val binding by viewBinding(FragmentEndBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        with(binding) {
//            btToMenu.setOnClickListener {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.main_container, MenuFragment::class.java, null, null)
//                    .commit()
//                //clear backstack to prevent some weird flow and not get back after order is finished
//                parentFragmentManager.clearAllBackStack()
//            }
//        }

        super.onViewCreated(view, savedInstanceState)
    }
}