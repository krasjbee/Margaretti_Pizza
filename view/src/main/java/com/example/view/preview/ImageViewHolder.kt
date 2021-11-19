package com.example.margarettipizza.presentation.preview

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.view.databinding.PreviewImageViewHolderBinding

class ImageViewHolder(private val binding: PreviewImageViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl: String) {
        with(binding) {
            Glide.with(root)
                .load(imageUrl)
                .into(ivPreviewPic)
        }
    }
}