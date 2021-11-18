package com.example.margarettipizza.presentation.cart

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.margarettipizza.R
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.databinding.PizzaCartCardBinding

class CartViewHolder(private val binding: PizzaCartCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        orderWithPizza: OrderWithPizza, onDecrementClick: (OrderWithPizza) -> Unit,
        onIncrementClick: (OrderWithPizza) -> Unit
    ) {
        val pizzaDto = orderWithPizza.pizzaDto
        val orderEntity = orderWithPizza.orderEntity
        with(binding) {
            tvPizzaName.text = pizzaDto.name
            //fixme move to usecase
            tvPizzaPrice.text = String.format(
                itemView.context.getString(R.string.ruble_symbol),
                pizzaDto.price.toInt()
            )
            tvQuantity.text = orderEntity.quantity.toString()
            Glide.with(this.root).load(pizzaDto.imageUrls.first()).into(sivPizzaCartPic)
            ibDecrement.setOnClickListener { onDecrementClick(orderWithPizza) }
            ibIncrement.setOnClickListener { onIncrementClick(orderWithPizza) }
        }
    }
}