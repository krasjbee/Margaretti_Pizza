package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.cart.CartFragment
import com.example.margarettipizza.presentation.details.DetailsDialog

class MenuFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            btFromHomeToCart.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, CartFragment::class.java, null, null)
                    .addToBackStack(null)
                    .commit()
            }

            btOpenDetails.setOnClickListener {
                val detailsDialog = DetailsDialog()
                detailsDialog.show(parentFragmentManager, "qwe")
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}