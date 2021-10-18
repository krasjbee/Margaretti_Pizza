package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentPreviewBinding
import com.example.margarettipizza.presentation.cart.CartFragment

class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            btFromPreviewToCart.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, CartFragment::class.java, null, null)
                    .addToBackStack(null)
                    .commit()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}