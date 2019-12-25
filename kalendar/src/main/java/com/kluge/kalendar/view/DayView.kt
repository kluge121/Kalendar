package com.kluge.kalendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.kluge.kalendar.R
import kotlinx.android.synthetic.main.layout_day.view.*

class DayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(
            R.layout.layout_day,
            this,
            true
        )
    }

    fun setDayText(day: String) {
        day_text.text = day
    }

    fun setDayText(day: Int) {
        day_text.text = day.toString()
    }

    fun showRangeBackground() {
        day_background_view.visibility = View.VISIBLE
    }

    fun hideRangeBackground() {
        day_background_view.visibility = View.INVISIBLE
    }

    fun showRangeStartAndEndBackGround() {
        day_text.setTextColor(context.getColor(R.color.white))
        day_text.setBackgroundResource(R.drawable.black_circle)
    }

    fun hideRangeStartAndEndBackGround() {
        day_text.setTextColor(context.getColor(R.color.white))
        day_text.setBackgroundResource(R.color.white)
    }

    fun setSubText(text: String) {
        day_sub_text.text = text
    }

    fun dayInvisible() {
        visibility = View.INVISIBLE
    }

    fun dayVisible() {
        visibility = View.VISIBLE
    }

}


