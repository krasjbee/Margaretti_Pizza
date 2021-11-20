package com.example.view.menu

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.PizzaEntity
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
            Glide.with(this.root)
                .load(pizza.imageUrls[0]).into(sivPizzaPic)
            sivPizzaPic.setOnClickListener {
                onClick(pizza)
            }
        }
    }
}