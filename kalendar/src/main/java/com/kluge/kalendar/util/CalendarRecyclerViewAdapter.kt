package com.kluge.kalendar.util

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.model.KMonth
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
}

class MonthViewHolder(
    view: View, val daysOfWeekConverter: (Int) -> Int
) :
    RecyclerView.ViewHolder(view) {

    fun bind(data: KMonth) {
        itemView.month_title.text = String.format(itemView.context.getString(R.string.month_title, data.year, data.month + 1))
        itemView.month_view.drawMonth(data, daysOfWeekConverter)
    }

}


