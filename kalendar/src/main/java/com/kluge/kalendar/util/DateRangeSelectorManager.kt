package com.kluge.kalendar.util

import android.widget.LinearLayout
import com.kluge.kalendar.view.DayView

object DateRangeSelectorManager {

    private var rangeSelectFlag: Boolean = false

    private var singleDayView: DayView? = null
    private var multiDayView1: DayView? = null
    private var multiDayView2: DayView? = null

    private var singleString: String? = null
    private var multiString1: String? = null
    private var multiString2: String? = null


    fun setSingleSelectMode(boolean: Boolean) {
        rangeSelectFlag = boolean
    }

    fun dayClick(dayView: DayView) {
        if (rangeSelectFlag) {
            multiModeClick(dayView)
        } else {
            singleModeClick(dayView)
        }
    }

    fun bindDayView(dayView: DayView) {
        dayView.hideRangeBackground()
        dayView.hideRangeStartAndEndBackGround()
        if (singleString == dayView.toString() || multiString1 == dayView.toString() || multiString2 == dayView.toString()) {
            dayView.showRangeStartAndEndBackGround()
        }

    }

    private fun singleModeClick(dayView: DayView) {
        singleDayView?.hideDayEffet()
        singleDayView = dayView.apply { showRangeStartAndEndBackGround() }
        singleString = dayView.toString()
    }

    private fun multiModeClick(dayView: DayView) {

        if (multiDayView1 == null) {
            dayView.showRangeStartAndEndBackGround()
            multiDayView1 = dayView
            multiString1 = dayView.toString()
        } else if (multiDayView2 == null) {
            dayView.showRangeStartAndEndBackGround()
            multiDayView2 = dayView
            multiString2 = dayView.toString()
            showDateBetweenRangeEffect()
        } else if (multiDayView1 != null && multiDayView2 != null) {
            stateClear()
            dayView.showRangeStartAndEndBackGround()
            hideDateBetweenRangeEffect()
            multiDayView1 = dayView
            multiString1 = dayView.toString()
        }
    }

    private fun showDateBetweenRangeEffect() {
        val startDayView: DayView
        val endDayView: DayView

        if (multiDayView1 != null && multiDayView2 != null) {
            if (multiDayView1!!.toInt() > multiDayView2!!.toInt()) {
                startDayView = multiDayView2!!
                endDayView = multiDayView1!!
            } else {
                startDayView = multiDayView1!!
                endDayView = multiDayView2!!
            }

            //DayView - WeekView - MonthItem(R.layout.layout_item_month)
            val root = (multiDayView1?.rootView?.rootView as LinearLayout)
            root.



        }


    }

    private fun hideDateBetweenRangeEffect() {

    }

    private fun stateClear() {

        singleDayView?.hideDayEffet()
        multiDayView1?.hideDayEffet()
        multiDayView2?.hideDayEffet()

        singleDayView = null
        multiDayView1 = null
        multiDayView2 = null

        singleString = null
        multiString1 = null
        multiString2 = null

    }
}