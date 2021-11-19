package com.example.margarettipizza.presentation.menu

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.margarettipizza.domain.entities.PizzaEntity
import com.example.view.databinding.PizzaListCardBinding

class PizzaViewHolder(private val binding: PizzaListCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        pizza: PizzaEntity,
        onClick: (PizzaEntity) -> Unit
    ) {
        with(binding) {
            tvPizzaName.text = pizza.name
            tvPizzaDescription.text = pizza.description
            tvPizzaCardPrice.text = pizza.price
            //TODO move picture download to viewmodel
            Glide.with(this.root)
                .load(pizza.imageUrls[0]).into(sivPizzaPic)
            sivPizzaPic.setOnClickListener {
                onClick(pizza)
            }
        }
    }
}