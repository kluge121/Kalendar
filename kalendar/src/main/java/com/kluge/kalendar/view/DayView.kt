package com.kluge.kalendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.kluge.kalendar.R
import com.kluge.kalendar.util.DateRangeSelectorManager
import kotlinx.android.synthetic.main.layout_day.view.*
import java.util.*


class DayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {

    var year: Int = 0
    var month: Int = 0
    var day: Int = 0

    init {
        LayoutInflater.from(context).inflate(
            R.layout.layout_day,
            this,
            true
        )
        setOnClickListener {
            DateRangeSelectorManager.dayClick(this)
        }
    }

    fun drawDay(calendar: Calendar, day: Int, endDay: Int) {
        if (day > endDay) {
            dayInvisible()
        } else {
            dayVisible()
            setDayText(day, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
            DateRangeSelectorManager.bindDayView(this)
        }
    }

    fun setDayText(day: Int, month: Int, year: Int) {
        this.day = day
        this.month = month
        this.year = year
        day_text.text = day.toString()
    }

    fun showRangeBackground() {
        day_background_view.visibility = View.VISIBLE
    }

    fun hideRangeBackground() {
        day_background_view.visibility = View.INVISIBLE
    }

    fun showRangeStartAndEndBackGround() {
        day_text.setTextColor(context.getColor(R.color.white))
        day_text.setBackgroundResource(R.drawable.black_circle)
    }

    fun hideRangeStartAndEndBackGround() {
        day_text.setTextColor(context.getColor(R.color.black))
        day_text.setBackgroundResource(0)
    }

    fun hideDayEffet() {
        hideRangeBackground()
        hideRangeStartAndEndBackGround()
    }

    fun setSubText(text: String) {
        day_sub_text.text = text
    }

    fun dayInvisible() {
        visibility = View.INVISIBLE
    }

    fun dayVisible() {
        visibility = View.VISIBLE
    }

    override fun toString(): String {
        val convertMonth = if (month < 10) "0$month" else month.toString()
        val convertDay = if (day < 10) "0$day" else day.toString()
        return "$year$convertMonth$convertDay"
    }

    fun toInt(): Int {
//        val firstNumRegExp = "^[1-9]+$".toRegex()
//        val NumRegExp = "^[0-9]+$".toRegex()
//        var result = this.toString()
//        return if (result[0].toString().matches(firstNumRegExp) &&
//            result.substring(1).matches(firstNumRegExp)
//        ) {
//            result.toInt()
//        } else {
//            -1
//        }
        return toString().toInt()
    }

    fun toMonthString(): String {
        val monthString = if (month < 10) "0$month" else month.toString()
        return "${year}${monthString}00"
    }
}


