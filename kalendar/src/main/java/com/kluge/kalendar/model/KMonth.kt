package com.kluge.kalendar.model

data class KMonth(
    val year: Int,
    val month: Int
) {
    override fun toString(): String {
        val monthString = if (month < 10) "0$month" else month.toString()
        return "${year}${monthString}00"
    }
}
