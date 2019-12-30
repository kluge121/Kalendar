package com.kluge.kalendar.util

import com.kluge.kalendar.view.DayView

object DateSelectorManager {

    private var rangeSelectFlag: Boolean = false

    private var singleDayView: DayView? = null
    private var multiDayView1: DayView? = null
    private var multiDayView2: DayView? = null

    private var singleString: String? = null
    private var multiString1: String? = null
    private var multiString2: String? = null

    private var adapter: CalendarRecyclerViewAdapter? = null

    fun setAdapter(adapter: CalendarRecyclerViewAdapter) {
        this.adapter = adapter
    }

    fun setSingleSelectMode(boolean: Boolean) {
        rangeSelectFlag = boolean
    }

    fun dayClick(dayView: DayView) {
        if (rangeSelectFlag) {
            multiModeClick(dayView)
        } else {
            singleModeClick(dayView)
        }
        adapter?.notifyDataSetChanged()
    }

    fun bindDayView(dayView: DayView) {
        dayView.hideRangeBackground()
        dayView.hideRangeStartAndEndBackGround()
        if (singleString == dayView.toString() || multiString1 == dayView.toString() || multiString2 == dayView.toString()) {
            dayView.showRangeStartAndEndBackGround()
        } else if (rangeSelectFlag && multiDayView1 != null && multiDayView2 != null) {
            val startString: String
            val endString: String

            if (multiDayView1!!.toInt() > multiDayView2!!.toInt()) {
                startString = multiString2!!
                endString = multiString1!!
            } else {
                startString = multiString1!!
                endString = multiString2!!
            }

            if (dayView.toInt() > startString.toInt() && dayView.toInt() < endString.toInt()) {
                dayView.showRangeBackground()
            }
        }
    }

    private fun singleModeClick(dayView: DayView) {
        singleDayView?.hideDayEffect()
        singleDayView = dayView.apply { showRangeStartAndEndBackGround() }
        singleString = dayView.toString()
    }

    private fun multiModeClick(dayView: DayView) {
        if (multiDayView1 == null) {
            dayView.showRangeStartAndEndBackGround()
            multiString1 = dayView.toString()
            multiDayView1 = dayView
        } else if (multiDayView2 == null) {
            dayView.showRangeStartAndEndBackGround()
            multiString2 = dayView.toString()
            multiDayView2 = dayView
        } else if (multiDayView1 != null && multiDayView2 != null) {
            stateClear()
            dayView.showRangeStartAndEndBackGround()
            multiString1 = dayView.toString()
            multiDayView1 = dayView
        }
    }

    private fun stateClear() {
        singleDayView?.hideDayEffect()
        multiDayView1?.hideDayEffect()
        multiDayView2?.hideDayEffect()

        singleDayView = null
        multiDayView1 = null
        multiDayView2 = null

        singleString = null
        multiString1 = null
        multiString2 = null
    }
}