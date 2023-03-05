package com.example.myapplication

import android.graphics.drawable.Drawable

data class AppInfo(
    val name: String,
    val icon: Drawable,
    val packageName: String,
    var isRunning: Boolean = false
)
