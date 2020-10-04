package com.absyntek.myappforus.models

import java.util.*

class Order (
    val date: Date,
    val price: Long,
    val productId: String,
    val quantity: Long
)