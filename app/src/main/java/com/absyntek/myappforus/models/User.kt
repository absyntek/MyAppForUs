package com.absyntek.myappforus.models

import com.absyntek.myappforus.ui.isNull
import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class User (
    val uid: String,
    var userName: String,
    var urlPicture: String,
    val isAdmin: Boolean,
    val dateCreated: Date
){
    companion object{
        fun createFromDocuument(doc:DocumentSnapshot): User?{
            val uid = doc.getString("uid")
            val userName = doc.getString("userName")
            val urlPicture = doc.getString("urlPicture")
            val isAdmin = doc.getBoolean("isAdmin")
            val dateCreated = doc.getDate("dateCreated")
            return if (isNull(uid,userName,urlPicture,isAdmin,dateCreated)) null
            else User(
                uid!!,
                userName!!,
                urlPicture!!,
                isAdmin!!,
                dateCreated!!,
            )
        }
    }
}