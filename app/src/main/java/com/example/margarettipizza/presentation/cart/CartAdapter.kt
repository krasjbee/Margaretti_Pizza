package com.example.margarettipizza.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.margarettipizza.data.local.orderDatabase.relations.OrderWithPizza
import com.example.margarettipizza.databinding.PizzaCartCardBinding

class CartAdapter(
    private val onDecrementClick: (OrderWithPizza) -> Unit,
    private val onIncrementClick: (OrderWithPizza) -> Unit,
) :
    ListAdapter<OrderWithPizza, CartViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PizzaCartCardBinding.inflate(inflater, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position), onDecrementClick, onIncrementClick)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<OrderWithPizza>() {
            override fun areItemsTheSame(
                oldItem: OrderWithPizza,
                newItem: OrderWithPizza
            ): Boolean = oldItem.orderEntity == newItem.orderEntity

            override fun areContentsTheSame(
                oldItem: OrderWithPizza,
                newItem: OrderWithPizza
            ): Boolean = oldItem.pizzaDto.price == newItem.pizzaDto.price

        }
    }
}