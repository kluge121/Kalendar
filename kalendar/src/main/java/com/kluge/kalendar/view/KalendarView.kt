package com.kluge.kalendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.model.Month
import com.kluge.kalendar.util.CalendarRecyclerViewAdapter
import com.kluge.kalendar.util.DateSelectorManager
import com.kluge.kalendar.util.DaysOfWeekUtil
import com.kluge.kalendar.util.toPx


class KalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var flag: Boolean = false
    private val recyclerView: RecyclerView
    private val adapter: CalendarRecyclerViewAdapter
    private val daysOfWeekTitle: WeekTitleView

    init {
        var startDaysOfWeek: Int

        context.obtainStyledAttributes(attrs, R.styleable.KalendarView).run {
            startDaysOfWeek = getInt(R.styleable.KalendarView_startDaysOfWeek, 1).let {
                if (it > 7 || it < 1) 1
                else it
            }
            DateSelectorManager.setSingleSelectMode(
                getBoolean(
                    R.styleable.KalendarView_rangeMode,
                    false
                )
            )
            recycle()
        }

        LayoutInflater.from(context).inflate(R.layout.layout_calendar, this, true)
            .also {
                daysOfWeekTitle = it.findViewById(R.id.daysOfWeekTitle)
                recyclerView = it.findViewById(R.id.calendar_recyclerview)
                DaysOfWeekUtil.setDaysOfIndex(daysOfWeekTitle, startDaysOfWeek)
                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter =
                    CalendarRecyclerViewAdapter(startDaysOfWeek = startDaysOfWeek)
                DateSelectorManager.setAdapter(adapter)
                recyclerView.adapter = adapter

                val array = arrayOf(
                    Month(2019, 0),
                    Month(2019, 1),
                    Month(2019, 2),
                    Month(2019, 3),
                    Month(2019, 4),
                    Month(2019, 5),
                    Month(2019, 6),
                    Month(2019, 7),
                    Month(2019, 8),
                    Month(2019, 9),
                    Month(2019, 10),
                    Month(2019, 11)
                )
                adapter.setMonths(*array)
                adapter.notifyDataSetChanged()

            }

        recyclerView.itemAnimator = DefaultItemAnimator()
    }


    fun collapse() {
        adapter.dayMode()
        layoutParams.height = 200.toPx(context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        daysOfWeekTitle.visibility = View.GONE
    }

    fun expand() {
        adapter.monthMode()
        layoutParams.height = 400.toPx(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        daysOfWeekTitle.visibility = View.VISIBLE
    }

}
