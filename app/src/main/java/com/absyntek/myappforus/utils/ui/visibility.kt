package com.absyntek.myappforus.utils.ui

import android.view.View
import androidx.core.view.isVisible

fun View.ensureVisible(){
    if (this.isVisible) return
    else this.visibility = View.VISIBLE
}

fun View.ensureGone(){
    if (!this.isVisible) return
    else this.visibility = View.GONE
}