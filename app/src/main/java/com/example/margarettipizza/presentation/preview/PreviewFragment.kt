package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.cart.CartFragment
import com.example.margarettipizza.utils.navigateTo

class PreviewFragment : Fragment(R.layout.fragment_preview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btToCart: Button = view.findViewById(R.id.bt_from_preview_to_cart)
        btToCart.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.main_container, CartFragment::class.java, null, null)
//                .addToBackStack(null)
//                .commit()
            navigateTo(CartFragment.getInstance())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun getInstance(): Fragment = PreviewFragment()
    }
}