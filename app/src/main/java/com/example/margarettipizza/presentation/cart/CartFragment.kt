package com.example.margarettipizza.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentCartBinding
import com.example.margarettipizza.presentation.end.EndFragment

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            btToEnd.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, EndFragment::class.java, null, null)
                    .addToBackStack(null)
                    .commit()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}