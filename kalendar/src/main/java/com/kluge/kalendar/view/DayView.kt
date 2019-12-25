package com.kluge.kalendar.view

import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_day.view.*


class DayView constructor(val dayContainerRoot: RelativeLayout) {

    fun setDayText(day: String) {
        dayContainerRoot.day_text.text = day
    }

    fun setDayText(day: Int) {
        dayContainerRoot.day_text.text = day.toString()
    }

    fun showRangeBackground() {
        dayContainerRoot.day_background_view.visibility = View.VISIBLE
    }

    fun hideRangeBackground() {
        dayContainerRoot.day_background_view.visibility = View.INVISIBLE
    }

    fun setSubText(text: String) {
        dayContainerRoot.day_sub_text.text = text
    }

    fun dayInvisible() {
        dayContainerRoot.visibility = View.INVISIBLE
    }
    fun dayVisible() {
        dayContainerRoot.visibility = View.VISIBLE
    }

}


