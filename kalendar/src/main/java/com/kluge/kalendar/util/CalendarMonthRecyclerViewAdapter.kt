package com.kluge.kalendar.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.model.Day
import com.kluge.kalendar.model.Month
import kotlinx.android.synthetic.main.layout_day.view.*
import kotlinx.android.synthetic.main.layout_item_day.view.*
import kotlinx.android.synthetic.main.layout_item_month.view.*


const val MONTH = 1
const val DAY = 2

class CalendarRecyclerViewAdapter(startDaysOfWeek: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val months = mutableListOf<Month>()
    private val days = mutableListOf<Day>()
    private val daysOfWeekConverter = DaysOfWeekUtil.makeApi(startDaysOfWeek)
    private var mode: Int = MONTH

    override fun getItemViewType(position: Int) = mode

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            MONTH -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_month, parent, false)
                MonthViewHolder(view, daysOfWeekConverter)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_day, parent, false)
                DayViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return when (mode) {
            MONTH -> months.size
            else -> days.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MonthViewHolder -> {
                holder.bind(months[position])
            }
            is DayViewHolder -> {
                holder.bind(days[position])
            }
        }
    }

    fun setMonths(vararg Months: Month) {
        this.months.clear()
        this.days.clear()
        for (month in Months) {
            this.months.add(month)
            this.days.addAll(month.days)
        }
    }

    fun monthMode() {
        mode = MONTH
    }

    fun dayMode() {
        mode = DAY
    }
}

class MonthViewHolder(
    view: View, private val daysOfWeekConverter: (Int) -> Int
) : RecyclerView.ViewHolder(view) {
    fun bind(data: Month) {
        itemView.month_title.text = String.format(
            itemView.context.getString(
                R.string.month_title,
                data.year,
                data.month + 1
            )
        )
        itemView.month_view.drawMonth(data, daysOfWeekConverter)
    }
}

class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(day: Day) {
        itemView.day_month_title.text = day.getYearAndMonthTitle()
        itemView.dayView.setDayText(day.day, day.month, day.year)
        itemView.dayView.setSubText(day.sub_title)
    }
}





