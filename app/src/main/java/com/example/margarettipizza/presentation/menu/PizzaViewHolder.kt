package com.example.margarettipizza.presentation.menu

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.margarettipizza.R
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.databinding.PizzaListCardBinding

class PizzaViewHolder(private val binding: PizzaListCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        pizza: PizzaDto,
        onClick: (PizzaDto) -> Unit
    ) {
        with(binding) {
            tvPizzaName.text = pizza.name
            tvPizzaDescription.text = pizza.description
            tvPizzaPrice.text = String.format(
                itemView.context.getString(R.string.ruble_symbol),
                pizza.price.toInt()
            )
            //TODO move picture download to viewmodel
            Glide.with(this.root)
                .load(pizza.imageUrls[0]).into(sivPizzaPic)
            sivPizzaPic.setOnClickListener {
                onClick(pizza)
            }
        }
    }
}