package com.example.margarettipizza.presentation.cart

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.margarettipizza.databinding.PizzaCartCardBinding
import com.example.margarettipizza.domain.entities.OrderAndPizzaEntity

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
            //fixme move to usecase
            tvPizzaPrice.text = pizzaDto.price
            tvQuantity.text = orderEntity.quantity.toString()
            Glide.with(this.root).load(pizzaDto.imageUrls.first()).into(sivPizzaCartPic)
            ibDecrement.setOnClickListener { onDecrementClick(orderWithPizza) }
            ibIncrement.setOnClickListener { onIncrementClick(orderWithPizza) }
        }
    }
}