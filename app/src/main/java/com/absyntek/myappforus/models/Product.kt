package com.absyntek.myappforus.models

import com.absyntek.myappforus.ui.isNull
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class Product (
    val name: String,
    val pictureUrl: String,
    val quantity: Long,
    val timeStart: Date,
    val indica :Long,
    val sativa : Long,
    val cbd: String,
    val thc: String,
    val content: String
){
    constructor() : this(
        name = "", pictureUrl = "", quantity = 50, timeStart= Date(), indica = 50, sativa = 50 , thc = "12", cbd = "moyen", content = ""
    )

    companion object{
        fun createFromDocuument(doc: DocumentSnapshot): Product?{
            val name = doc.getString("nama")
            val pictureUrl = doc.getString("pictureUrl")?: ""
            val quantity = doc.getLong("quantity")
            val timeStart = doc.getDate("timeStart")
            val indica = doc.getLong("indica")?: 50
            val sativa = doc.getLong("sativa")?: 50
            val thc= doc.getString("thc")?: ""
            val cbd= doc.getString("cbd")?: ""
            val content = doc.getString("desc")?: ""

            return if (isNull(name,quantity,timeStart)) null
            else Product(
                name!!,
                pictureUrl,
                quantity!!,
                timeStart!!,
                indica, sativa, cbd, thc, content
            )
        }
    }
}