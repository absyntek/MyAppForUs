package com.absyntek.myappforus.ui

fun isNull(vararg toCheck: Any?): Boolean{
    toCheck.forEach {
        if (it == null) return true
    }
    return false
}