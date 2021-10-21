package com.example.margarettipizza.presentation.menu

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.margarettipizza.R
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
            tvPizzaPrice.text = String.format(
                itemView.context.getString(R.string.ruble_symbol),
                pizza.price.toInt()
            )
            //TODO add picture load
            //sivPizzaPic.setImageDrawable()
            //TODO move picture download to viewmodel
            Glide.with(this.root)
                .load(pizza.imageUrl).into(sivPizzaPic)
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