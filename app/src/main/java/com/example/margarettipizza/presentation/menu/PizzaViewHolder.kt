package com.example.margarettipizza.presentation.menu

import androidx.recyclerview.widget.RecyclerView
import com.example.margarettipizza.data.apiStub.PizzaEntity
import com.example.margarettipizza.databinding.PizzaListCardBinding

class PizzaViewHolder(private val binding: PizzaListCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        pizza: PizzaEntity,
        onClick: (PizzaEntity) -> Unit
    ) {
        with(binding) {
            tvPizzaName.text = pizza.name
            tvPizzaDescription.text = pizza.description
            //TODO format string
            tvPizzaPrice.text = pizza.price.toString()
            //TODO add picture load
            //sivPizzaPic.setImageDrawable()
            sivPizzaPic.setOnClickListener {
                onClick(pizza)
            }
            // FIXME: 19.10.2021 DELETE
            root.setOnClickListener {
                onClick(pizza)
            }
        }
    }
}