package com.absyntek.myappforus.models

import com.absyntek.myappforus.ui.isNull
import com.google.firebase.firestore.DocumentSnapshot

class Message (
    val text: String,
    val sender: String
){
    constructor() : this(
        "Error","Error"
    )

    companion object{
        fun createFromDocument(doc: DocumentSnapshot): Message?{
            val text = doc.getString("text")
            val sender = doc.getString("sender")

            return if (isNull(text, sender)) null
            else Message(
                text!!,
                sender!!
            )
        }
    }
}