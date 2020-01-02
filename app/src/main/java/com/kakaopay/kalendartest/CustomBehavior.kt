package com.kakaopay.kalendartest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.kluge.kalendar.util.toPx
import com.kluge.kalendar.view.KalendarView

class CalendarCollapseBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<KalendarView>() {

    private var flag: Boolean = true


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: KalendarView,
        dependency: View
    ): Boolean {
        return dependency.id == R.id.test_recyclerview
    }


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: KalendarView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: KalendarView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

        val firstItmePosition =
            ((target as RecyclerView).layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

        if (flag && firstItmePosition != 0) {
            child.collapse()
            flag = false
        } else if (firstItmePosition == 0) {
            child.expand()
            flag = true
        }


    }


    private fun collapseView(view: KalendarView) {
        view.collapse()
    }

    private fun expandView(view: KalendarView) {
        view.expand()
    }

}
