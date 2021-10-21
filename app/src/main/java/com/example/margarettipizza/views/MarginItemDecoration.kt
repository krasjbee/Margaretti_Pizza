package com.example.margarettipizza.views

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class MarginItemDecoration(private val context: Context, private val spaceSizeDp: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spaceSizePx = (spaceSizeDp * context.resources.displayMetrics.density).roundToInt()
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSizePx
            }
            left = spaceSizePx
            right = spaceSizePx
            bottom = spaceSizePx
        }
    }
}