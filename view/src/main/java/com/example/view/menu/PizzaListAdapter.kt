package com.example.margarettipizza.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.margarettipizza.databinding.PizzaListCardBinding
import com.example.margarettipizza.domain.entities.PizzaEntity

class PizzaListAdapter(private val onClick: (PizzaEntity) -> Unit) :
    ListAdapter<PizzaEntity, PizzaViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PizzaListCardBinding.inflate(inflater, parent, false)
        return PizzaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<PizzaEntity>() {
            override fun areItemsTheSame(oldItem: PizzaEntity, newItem: PizzaEntity): Boolean =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: PizzaEntity, newItem: PizzaEntity): Boolean =
                newItem.name == oldItem.name &&
                        newItem.description == oldItem.description &&
                        newItem.imageUrls.first() == oldItem.imageUrls.first() &&
                        newItem.price == oldItem.price
        }
    }
}