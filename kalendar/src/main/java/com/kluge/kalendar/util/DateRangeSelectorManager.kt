package com.kluge.kalendar.util

import com.kluge.kalendar.view.DayView
import com.kluge.kalendar.view.KalendarView

class DateRangeSelectorManager constructor(val kalendarView: KalendarView) {

    private var startDayView: DayView? = null
    private var endDayView: DayView? = null
    private var singleDayView: DayView? = null

    var rangeSelectFlag: Boolean = false

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

    private fun singleModeClick(dayView: DayView) {
        singleDayView?.hideRangeStartAndEndBackGround()
        singleDayView = dayView.apply { showRangeStartAndEndBackGround() }
    }

    private fun multiModeClick(dayView: DayView) {

        if (startDayView == null || endDayView == null) {
            dayView.showRangeStartAndEndBackGround()
        } else {
            showDateRagneBackgorund()
        }
    }

    private fun showDateRagneBackgorund() {
        if (startDayView != null && endDayView != null) {


        }
    }

    private fun hideDateRagneBackgorund() {
        if (startDayView != null && endDayView != null) {


        }
    }


    private fun stateClear() {

        if (rangeSelectFlag) {
            startDayView?.hideRangeStartAndEndBackGround()
            endDayView?.hideRangeStartAndEndBackGround()
            hideDateRagneBackgorund()
        } else {
            singleDayView?.hideRangeBackground()
        }

        startDayView = null
        endDayView = null
        singleDayView = null

    }
}