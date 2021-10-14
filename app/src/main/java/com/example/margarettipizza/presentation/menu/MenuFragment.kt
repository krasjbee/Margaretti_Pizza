package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.cart.CartFragment
import com.example.margarettipizza.presentation.details.DetailsDialog

class MenuFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btOpenCart = view.findViewById<Button>(R.id.bt_from_home_to_cart)
        val btOpenDetails = view.findViewById<Button>(R.id.bt_open_details)
        btOpenCart.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, CartFragment::class.java, null, null)
                .addToBackStack(null)
                .commit()
        }
        btOpenDetails.setOnClickListener {
            val detailsDialog = DetailsDialog()
            detailsDialog.show(parentFragmentManager, "qwe")
        }

//        val callback= object :OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                exitProcess(0)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        super.onViewCreated(view, savedInstanceState)
    }

}