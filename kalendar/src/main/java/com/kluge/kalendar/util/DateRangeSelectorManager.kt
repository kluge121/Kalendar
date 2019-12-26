package com.kluge.kalendar.util

import android.util.Log
import com.kluge.kalendar.view.DayView

object DateRangeSelectorManager {

    private var rangeSelectFlag: Boolean = false

    private var startDayView: DayView? = null
    private var endDayView: DayView? = null
    private var singleDayView: DayView? = null

    private var startString: String? = null
    private var endString: String? = null
    private var singleString: String? = null

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

    fun bindSupportDayView(dayView: DayView) {
        dayView.hideRangeBackground()
        dayView.hideRangeStartAndEndBackGround()
        //TODO 범위 선택에 따른 중간 range 영역 선택
        if (startString == dayView.toString() || endString == dayView.toString() || singleString == dayView.toString()) {
            dayView.showRangeStartAndEndBackGround()
        } else if (rangeSelectFlag) {
            Log.e("체크", "중간 영역")
            dayView.hideRangeStartAndEndBackGround()
        }
    }

    private fun singleModeClick(dayView: DayView) {
        singleDayView?.hideRangeStartAndEndBackGround()
        singleDayView = dayView.apply { showRangeStartAndEndBackGround() }
        singleString = dayView.toString()
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
        startString = ""
        endString = ""
        singleString = ""
    }
}