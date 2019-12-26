package com.kluge.kalendar.util

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.view.DayView
import kotlinx.android.synthetic.main.layout_item_month.view.*
import java.util.*


class CalendarRecyclerViewAdapter(startDaysOfWeek: Int) :
    RecyclerView.Adapter<MonthViewHolder>() {

    private val months = mutableListOf<KMonth>()
    private val monthMap = mutableMapOf<String, KMonth>()
    private val daysOfWeekConverter = DaysOfWeekUtil.makeApi(startDaysOfWeek)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_month, parent, false)
        return MonthViewHolder(view, daysOfWeekConverter)
    }

    override fun getItemCount(): Int {
        return months.size
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bind(months[position])
    }

    fun setMonths(vararg KMonths: KMonth) {
        this.months.clear()
        this.monthMap.clear()
        for (month in KMonths) {
            this.months.add(month)
            this.monthMap["$month"] = month
        }
    }

    fun findMonth(year: Int, month: Int) {


    }
}

class MonthViewHolder(
    view: View, val daysOfWeekConverter: (Int) -> Int
) :
    RecyclerView.ViewHolder(view) {

    fun bind(data: KMonth) {
        itemView.month_title.text =
            String.format(
                itemView.context.getString(
                    R.string.month_title,
                    data.year,
                    data.month + 1
                )
            )
        drawMonths(data)
    }

    private fun drawMonths(data: KMonth) {
        val root = (itemView as ViewGroup)

        //Calendar, 해당 달의 1일로 설정
        val calendar = Calendar.getInstance().apply {
            set(data.year, data.month, 1)
        }

        var inputDayValue = 1
        val endDay = calendar.getActualMaximum(Calendar.DATE)
        var columnDaysOfWeek =
            daysOfWeekConverter(calendar.get(Calendar.DAY_OF_WEEK))

        for (i in 1 until root.childCount) {
            val childView = root[i].also {

                //draw week
                val weekContainer = (it as ViewGroup)

                //해당 주에 날짜가 하나도 없으면 해당 week 숨기
                if (inputDayValue >= endDay) {
                    weekContainer.visibility = GONE
                } else {
                    weekContainer.visibility = VISIBLE
                }

                //해당 주 날짜 입력
                for (j in 0..6) {
                    val dayContainer = (weekContainer[j] as DayView)

                    //각 달의 첫째 주 시작요일 이전까지 invisible
                    if (columnDaysOfWeek > 0 && j <= columnDaysOfWeek - 1) {
                        drawDay(dayContainer, endDay + 1, endDay)
                    } else {
                        drawDay(dayContainer, inputDayValue, endDay)
                        inputDayValue++
                    }
                }
                columnDaysOfWeek = 0
                calendar.add(Calendar.WEEK_OF_MONTH, 1)

            }
        }
    }

    private fun drawDay(dayView: DayView, day: Int, endDay: Int) {
        if (day > endDay) {
            dayView.dayInvisible()
        } else {
            dayView.dayVisible()
            dayView.setDayText(day)
        }
        DateRangeSelectorManager.bindSupportDayView(dayView)
    }
}

data class KMonth(
    val year: Int,
    val month: Int
) {
    override fun toString(): String {
        val monthText = if (month < 10) "0$month" else month.toString()
        return "$year$monthText"
    }
}

