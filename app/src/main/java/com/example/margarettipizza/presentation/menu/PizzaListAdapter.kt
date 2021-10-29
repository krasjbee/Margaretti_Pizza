package com.example.margarettipizza.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.databinding.PizzaListCardBinding

class PizzaListAdapter(private val onClick: (PizzaDto) -> Unit) :
    ListAdapter<PizzaDto, PizzaViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PizzaListCardBinding.inflate(inflater, parent, false)
        return PizzaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<PizzaDto>() {
            override fun areItemsTheSame(oldItem: PizzaDto, newItem: PizzaDto): Boolean =
                newItem.id == oldItem.id

            override fun areContentsTheSame(oldItem: PizzaDto, newItem: PizzaDto): Boolean =
                newItem.name == oldItem.name &&
                        newItem.description == oldItem.description &&
                        newItem.imageUrls[0] == oldItem.imageUrls[0] &&
                        newItem.price == oldItem.price
        }
    }
}