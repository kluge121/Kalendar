package com.kluge.kalendar.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kluge.kalendar.R
import com.kluge.kalendar.util.CalendarRecyclerViewAdapter
import com.kluge.kalendar.util.DateRangeSelectorManager
import com.kluge.kalendar.util.DaysOfWeekUtil
import com.kluge.kalendar.util.KMonth


class KalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

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
            DateRangeSelectorManager.setSingleSelectMode(
                getBoolean(
                    R.styleable.KalendarView_rangeMode,
                    false
                )
            )
        }

        LayoutInflater.from(context).inflate(R.layout.layout_calendar, this, true)
            .also {
                daysOfWeekTitle = it.findViewById(R.id.daysOfWeekTitle)
                recyclerView = it.findViewById(R.id.calendar_recyclerview)
                DaysOfWeekUtil.setDaysOfIndex(daysOfWeekTitle, startDaysOfWeek)
                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter =
                    CalendarRecyclerViewAdapter(startDaysOfWeek = startDaysOfWeek)
                recyclerView.adapter = adapter

                val array = arrayOf(
                    KMonth(2019, 0),
                    KMonth(2019, 1),
                    KMonth(2019, 2),
                    KMonth(2019, 3),
                    KMonth(2019, 4),
                    KMonth(2019, 5),
                    KMonth(2019, 6),
                    KMonth(2019, 7),
                    KMonth(2019, 8),
                    KMonth(2019, 9),
                    KMonth(2019, 10),
                    KMonth(2019, 11)
                )
                adapter.setMonths(*array)
                adapter.notifyDataSetChanged()

            }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

    }


}
