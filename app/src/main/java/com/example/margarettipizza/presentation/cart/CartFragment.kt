package com.example.margarettipizza.presentation.cart

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.end.EndFragment

class CartFragment : Fragment(R.layout.fragment_cart) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btToEnd = view.findViewById<Button>(R.id.bt_to_end)
        btToEnd.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, EndFragment::class.java, null, null)
                .addToBackStack(null)
                .commit()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}