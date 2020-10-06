package com.absyntek.myappforus.models

import com.absyntek.myappforus.ui.isNull
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class Message (
    val text: String,
    val sender: String,
    val cratedAt: Date
){
    constructor() : this(
        "Error","Error", Date()
    )

    companion object{
        fun createFromDocument(doc: DocumentSnapshot): Message?{
            val text = doc.getString("text")
            val sender = doc.getString("sender")
            val createdAt = doc.getDate("createdAt")

            return if (isNull(text, sender, createdAt)) null
            else Message(
                text!!,
                sender!!,
                createdAt!!
            )
        }
    }
}