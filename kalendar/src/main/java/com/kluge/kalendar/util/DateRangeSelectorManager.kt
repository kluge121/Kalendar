package com.kluge.kalendar.util

import android.util.Log
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
        } else if (rangeSelectFlag && multiDayView1 != null && multiDayView2 != null) {
            val startDayView: DayView
            val endDayView: DayView

            if (multiDayView1!!.toInt() > multiDayView2!!.toInt()) {
                startDayView = multiDayView2!!
                endDayView = multiDayView1!!
            } else {
                startDayView = multiDayView1!!
                endDayView = multiDayView2!!
            }

            if (dayView.toInt() > startDayView.toInt() && dayView.toInt() < endDayView.toInt()) {
                Log.e("체크", "바인드체크")
//                dayView.showRangeBackground()
            }
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
            multiString1 = dayView.toString()
            multiDayView1 = dayView
        } else if (multiDayView2 == null) {
            dayView.showRangeStartAndEndBackGround()
            multiString2 = dayView.toString()
            multiDayView2 = dayView
            showDateBetweenRangeEffect()
        } else if (multiDayView1 != null && multiDayView2 != null) {
            stateClear()
            multiString1 = dayView.toString()
            multiDayView1 = dayView
            dayView.showRangeStartAndEndBackGround()
            hideDateBetweenRangeEffect()

        }
    }

    private fun showDateBetweenRangeEffect() {

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