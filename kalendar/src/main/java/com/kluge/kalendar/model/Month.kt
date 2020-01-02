package com.kluge.kalendar.model

import java.util.*
import kotlin.collections.ArrayList

data class Month(
    val year: Int,
    val month: Int,
    val days: ArrayList<Day> = ArrayList()
) {

    init {
        generateDay()
    }

    override fun toString(): String {
        val monthString = if (month < 10) "0$month" else month.toString()
        return "${year}${monthString}00"
    }

    private fun generateDay() {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, 1)
        val lastDay = calendar.getActualMaximum(Calendar.DATE)
        for (i in 1..lastDay) {
            days.add(Day(year,month,i))
        }
    }
}
