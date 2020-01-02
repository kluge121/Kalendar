package com.kluge.kalendar.model

data class Day(
    val year: Int,
    val month: Int,
    val day: Int,
    val sub_title: String = ""
) {
    fun getYearAndMonthTitle(): String {
        return if (day == 1) "${year}년 ${month + 1}월"
        else ""
    }

}