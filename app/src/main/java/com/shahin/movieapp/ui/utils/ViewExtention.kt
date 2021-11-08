package com.shahin.movieapp.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun marginItemDecoration(
    marginLeft: Int = 0,
    marginRight: Int = 0,
    marginTop: Int = 0,
    marginBottom: Int = 0,
) = object : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            left = marginLeft
            right = marginRight
            top = marginTop
            bottom = marginBottom
        }
    }

}