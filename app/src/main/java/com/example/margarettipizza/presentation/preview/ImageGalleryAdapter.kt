package com.example.margarettipizza.presentation.preview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.margarettipizza.databinding.PreviewImageViewHolderBinding

class ImageGalleryAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    private var list = emptyList<String>()

    fun setList(list: List<String>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PreviewImageViewHolderBinding.inflate(inflater, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}