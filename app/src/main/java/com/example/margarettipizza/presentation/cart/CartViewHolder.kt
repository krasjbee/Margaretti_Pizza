package com.example.margarettipizza.presentation.cart

import androidx.recyclerview.widget.RecyclerView
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.databinding.PizzaCartCardBinding

class CartViewHolder(private val binding: PizzaCartCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(orderWithPizza: OrderWithPizza) {
        val pizzaDto = orderWithPizza.pizzaDto
        val orderEntity = orderWithPizza.orderEntity
        with(binding) {
            tvPizzaName.text = pizzaDto.name
            tvPizzaPrice.text = pizzaDto.price.toInt().toString()
            tvQuantity.text = orderEntity.quantity.toString()
        }
    }
}