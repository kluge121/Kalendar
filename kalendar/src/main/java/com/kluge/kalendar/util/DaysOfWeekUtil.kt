package com.kluge.kalendar.util

import android.widget.LinearLayout
import android.widget.TextView
import com.kluge.kalendar.R
import com.kluge.kalendar.view.WeekTitleView

object DaysOfWeekUtil {

    private var weekTitle = arrayOf(
        -1,
        R.string.sun,
        R.string.mon,
        R.string.tue,
        R.string.wed,
        R.string.thu,
        R.string.fri,
        R.string.sat
    )

    /**
     *  들어오는 숫자를 1~7 사이로 모듈러 연산,
     *  java calendar daysOfWeek 범위 안에서 사용할 수 있도록 변환함
     * @param startDaysOfWeek Java Calendar daysOfWeek type
     */
    private fun daysOfWeekIndexBoundAutoConverter(daysOfWeek: Int): Int {
        val index = daysOfWeek % 7
        return when {
            daysOfWeek > 7 -> index
            daysOfWeek < 0 -> daysOfWeekIndexBoundAutoConverter(7 + index)
            else -> daysOfWeek
        }
    }

    /**
     *  해당 인자 값이 들어오는 요일이 가장 왼쪽에 오는 요일이 됨
     * @param startDaysOfWeek Java Calendar daysOfWeek type
     */
    fun makeApi(startDaysOfWeek: Int): (Int) -> Int {
        return {
            when (it) {
                startDaysOfWeek -> 0
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 1) -> 1
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 2) -> 2
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 3) -> 3
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 4) -> 4
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 5) -> 5
                daysOfWeekIndexBoundAutoConverter(startDaysOfWeek + 6) -> 6
                else -> 0
            }
        }
    }

    /**
     * 요일의 타이틀을 실제로 입력
     * @param viewGroup daysOfWeek root view
     * @param startDaysOfWeek 달력 가장 왼쪽에 오는 요일 설정, Java Calendar daysOfWeek type
     */
    fun setDaysOfIndex(viewGroup: WeekTitleView, startDaysOfWeek: Int) {
        var localIndex = startDaysOfWeek
        val root = viewGroup.getChildAt(0) as LinearLayout
        for (i in 0 until root.childCount) {
            val daysOfWeekTextView = (root.getChildAt(i) as TextView)
            daysOfWeekTextView.text = viewGroup.context.getString(weekTitle[localIndex])
            localIndex = daysOfWeekIndexBoundAutoConverter(localIndex + 1)
        }

    }
}