package com.kluge.kalendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.kluge.kalendar.R

class WeekView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(
            R.layout.layout_week,
            this,
            true
        )
    }

    /**
     * 해당 week의 자식 day들을 범위로 선택영역 backgound를 설정하거나 적용
     * 주의! 영역 선택의 시작점과 끝점의 선택이 아닌 중간에 포함된 영역을 색칠할 때 사용
     * @param startDaysOfWeekf select start range,  0~6, 0은 KalendarView에서 설정한 시작 요일이 됨
     * @param endDaysOfWeekf select end range,      0~6, 0은 KalendarView에서 설정한 시작 요일이 됨
     */
    fun showWeekRangeSelectBackground(startDaysOfWeek: Int, endDaysOfWeek: Int) {
        for (i in startDaysOfWeek..endDaysOfWeek) {
            val dayView = (getChildAt(i) as DayView)
            dayView.showRangeBackground()
        }
    }

    fun showFullWeekRangeSelectBackground() {
        showWeekRangeSelectBackground(0, 6)
    }

    fun hideWeekRangeSelectBackground(startDaysOfWeek: Int, endDaysOfWeek: Int) {
        for (i in startDaysOfWeek..endDaysOfWeek) {
            val dayView = (getChildAt(i) as DayView)
            dayView.hideRangeBackground()
        }
    }

    fun hideFullWeekRangeSelectBackground() {
        hideWeekRangeSelectBackground(0, 6)
    }
}


