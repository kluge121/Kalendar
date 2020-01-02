package com.kluge.kalendar

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView


class ItemAnimator : DefaultItemAnimator() {

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {

        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY)
    }
}
