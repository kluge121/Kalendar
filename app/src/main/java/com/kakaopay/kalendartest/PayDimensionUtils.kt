package com.kluge.kalendar.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.view.View

fun Context.getDensity(): Float {
    return resources.getDensity()
}

fun Resources.getDensity(): Float {
    return displayMetrics.density
}

fun Int.toPx(context: Context): Int {
    return this.toFloat().toPx(context)
}

fun Int.toPx(resource: Resources): Int {
    return this.toFloat().toPx(resource)
}

fun Float.toPx(context: Context): Int {
    return toPx(context.resources)
}

fun Float.toPx(resource: Resources): Int {
    return (this * resource.getDensity()).toInt()
}

fun Int.toDp(context: Context): Float {
    return toFloat().toDp(context.resources)
}

fun Int.toDp(resource: Resources): Float {
    return toFloat().toDp(resource)
}

fun Float.toDp(context: Context): Float {
    return toDp(context.resources)
}

fun Float.toDp(resource: Resources): Float {
    return (this / resource.getDensity())
}

fun Context.screenRatio(): Float {
    val size = getScreenSize()
    return size.x.toFloat() / size.y.toFloat()
}

fun Context.getScreenSize(): Point {
    return Point().also {
        obtainWindowManager().defaultDisplay.getSize(it)
    }
}

fun View.getLocation(): Point {
    return IntArray(2).let {
        getLocationOnScreen(it)
        Point().apply {
            x = it[0]
            y = it[1]
        }
    }
}

fun View.getLocationTop(): Int {
    return getLocation().y
}

fun View.getLocationLeft(): Int {
    return getLocation().x
}

fun View.getLocationBottom(): Int {
    return getLocationTop() + height
}

fun View.getLocationRight(): Int {
    return getLocationLeft() + width
}