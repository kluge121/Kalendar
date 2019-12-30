package com.kluge.kalendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.get
import com.kluge.kalendar.R
import com.kluge.kalendar.model.Month
import java.util.*

class MonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(
            R.layout.layout_month,
            this,
            true
        )
    }

    fun drawMonth(data: Month, daysOfWeekConverter: (Int) -> Int) {

        //Calendar, 해당 달의 1일로 설정
        val calendar = Calendar.getInstance().apply {
            set(data.year, data.month, 1)
        }

        var inputDayValue = 1
        val endDay = calendar.getActualMaximum(Calendar.DATE)
        var columnDaysOfWeek = daysOfWeekConverter(calendar.get(Calendar.DAY_OF_WEEK))

        val childCount = this.childCount
        for (i in 0 until childCount) {
            val weekContainer = this.getChildAt(i) as WeekView

            //해당 주에 날짜가 하나도 없으면 해당 week 숨기기
            if (inputDayValue > endDay) {
                weekContainer.visibility = View.GONE
            } else {
                weekContainer.visibility = View.VISIBLE
            }

            //해당 주 날짜 입력
            for (j in 0..6) {
                val dayContainer = (weekContainer[j] as DayView)

                //각 달의 첫째 주 시작요일 이전까지 invisible
                if (columnDaysOfWeek > 0 && j <= columnDaysOfWeek - 1) {
                    dayContainer.drawDay(calendar = calendar, day = endDay + 1, endDay = endDay)
                } else {
                    dayContainer.drawDay(calendar = calendar, day = inputDayValue, endDay = endDay)
                    inputDayValue++
                }
            }
            columnDaysOfWeek = 0
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }
    }

}