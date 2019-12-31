package com.kluge.kalendar.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.model.Month
import kotlinx.android.synthetic.main.layout_item_month.view.*


class CalendarRecyclerViewAdapter(startDaysOfWeek: Int) :
    RecyclerView.Adapter<MonthViewHolder>() {

    private val months = mutableListOf<Month>()
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

    fun setMonths(vararg Months: Month) {
        this.months.clear()
        for (month in Months) {
            this.months.add(month)
        }
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


