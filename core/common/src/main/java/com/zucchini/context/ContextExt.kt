package com.zucchini.context

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager

fun Context.dialogViewPercent(
    dialog: Dialog?,
    widthPercent: Double = 0.8,
    heightPercent: Double = 0.8,
) {
    val deviceSize = getDeviceSize()
    dialog?.window?.run {
        val params = attributes
        params.width = (deviceSize[0] * widthPercent).toInt()
        params.height = (deviceSize[1] * heightPercent).toInt()
        attributes = params
    }
}

fun Context.dialogWidthPercent(
    dialog: Dialog?,
    widthPercent: Double = 0.8,
) {
    val deviceSize = getDeviceSize()
    dialog?.window?.run {
        val params = attributes
        params.width = (deviceSize[0] * widthPercent).toInt()
        attributes = params
    }
}

fun Context.getDeviceSize(): IntArray {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val windowInsets = windowMetrics.windowInsets

        val insets =
            windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout(),
            )
        val insetsWidth = insets.right + insets.left
        val insetsHeight = insets.top + insets.bottom

        val bounds = windowMetrics.bounds

        return intArrayOf(bounds.width() - insetsWidth, bounds.height() - insetsHeight)
    } else {
        val display = windowManager.defaultDisplay
        val size = Point()

        display?.getSize(size)

        return intArrayOf(size.x, size.y)
    }
}
