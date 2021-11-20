package com.example.view.cart

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.OrderAndPizzaEntity
import com.example.view.databinding.PizzaCartCardBinding

class CartViewHolder(private val binding: PizzaCartCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        orderWithPizza: OrderAndPizzaEntity, onDecrementClick: (OrderAndPizzaEntity) -> Unit,
        onIncrementClick: (OrderAndPizzaEntity) -> Unit
    ) {
        val pizzaDto = orderWithPizza.pizzaEntity
        val orderEntity = orderWithPizza.orderEntity
        with(binding) {
            tvPizzaName.text = pizzaDto.name
            tvPizzaPrice.text = pizzaDto.price
            tvQuantity.text = orderEntity.quantity.toString()
            Glide.with(this.root).load(pizzaDto.imageUrls.first()).into(sivPizzaCartPic)
            ibDecrement.setOnClickListener { onDecrementClick(orderWithPizza) }
            ibIncrement.setOnClickListener { onIncrementClick(orderWithPizza) }
        }
    }
}