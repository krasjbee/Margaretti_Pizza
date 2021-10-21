package com.example.margarettipizza.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.DialogDetailsBinding
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsDialog : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogDetailsBinding::bind)
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments ?: throw Exception("No element passed")
        val id = args.getInt(MenuFragment.PIZZA_PASSED_ID_KEY)

        viewModel.getPizzaById(id)
        viewModel.pizza.observe(viewLifecycleOwner) { pizza ->
            with(binding) {
                Glide.with(this@DetailsDialog)
                    .load(pizza.imageUrl)
                    .into(sivPizzaPic)
                tvPizzaName.text = pizza.name
                tvPizzaDescription.text = pizza.description
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}