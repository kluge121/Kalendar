package com.kluge.kalendar.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes

fun Context.getActivity(): Activity? {
    var contextHolder = this
    while (contextHolder is ContextWrapper) {
        if (contextHolder is Activity) {
            return contextHolder
        }
        contextHolder = contextHolder.baseContext
    }
    return null
}

fun Context.forceShowKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.forceHideKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

/** display **/
fun Context.obtainWindowManager(): WindowManager {
    return getSystemService(Context.WINDOW_SERVICE) as WindowManager
}

fun Context.getRealMetricsTo(to: DisplayMetrics) {
    return obtainWindowManager().defaultDisplay.getRealMetrics(to)
}

fun Context.getRealMetrics(): DisplayMetrics {
    return DisplayMetrics().also { obtainWindowManager().defaultDisplay.getRealMetrics(it) }
}

fun Context.getMetricsTo(to: DisplayMetrics) {
    return obtainWindowManager().defaultDisplay.getMetrics(to)
}

fun Context.getMetrics(): DisplayMetrics {
    return DisplayMetrics().also { obtainWindowManager().defaultDisplay.getMetrics(it) }
}

fun Context.obtainDisplayOrientation(): Int {
    return obtainWindowManager().defaultDisplay.rotation
}

fun Context.getNavigationBarHeight(): Int {
    val resId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resId > 0) {
        resources.getDimensionPixelSize(resId)
    } else {
        0
    }
}

fun Context.inflate(@LayoutRes id: Int, parent: ViewGroup, attachToRoot: Boolean): View {
    apply {  }
    return LayoutInflater.from(this).inflate(id, parent, attachToRoot)
}

fun Context.getStatusBarHeight(): Int {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resId > 0) {
        resources.getDimensionPixelSize(resId)
    } else {
        0
    }
}