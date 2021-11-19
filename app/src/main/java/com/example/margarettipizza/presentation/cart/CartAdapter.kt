package com.example.margarettipizza.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.margarettipizza.databinding.PizzaCartCardBinding
import com.example.margarettipizza.domain.entities.OrderAndPizzaEntity

class CartAdapter(
    private val onDecrementClick: (OrderAndPizzaEntity) -> Unit,
    private val onIncrementClick: (OrderAndPizzaEntity) -> Unit,
) :
    ListAdapter<OrderAndPizzaEntity, CartViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PizzaCartCardBinding.inflate(inflater, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position), onDecrementClick, onIncrementClick)
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<OrderAndPizzaEntity>() {
            override fun areItemsTheSame(
                oldItem: OrderAndPizzaEntity,
                newItem: OrderAndPizzaEntity
            ): Boolean = oldItem.orderEntity == newItem.orderEntity

            override fun areContentsTheSame(
                oldItem: OrderAndPizzaEntity,
                newItem: OrderAndPizzaEntity
            ): Boolean = oldItem.pizzaEntity.price == newItem.pizzaEntity.price

        }
    }
}