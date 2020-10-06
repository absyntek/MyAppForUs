package com.absyntek.myappforus.models

import android.os.Parcelable
import com.absyntek.myappforus.ui.isNull
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class User (
    val uid: String,
    var userName: String,
    var urlPicture: String,
    val admin: Boolean,
    val dateCreated: Date
) : Parcelable {
    constructor(): this("","","",false,Date())
    companion object{
        fun createFromDocuument(doc:DocumentSnapshot): User?{
            val uid = doc.getString("uid")
            val userName = doc.getString("userName")
            val urlPicture = doc.getString("urlPicture")
            val admin = doc.getBoolean("admin")
            val dateCreated = doc.getDate("dateCreated")
            return if (isNull(uid,userName,urlPicture,admin,dateCreated)) null
            else User(
                uid!!,
                userName!!,
                urlPicture!!,
                admin!!,
                dateCreated!!,
            )
        }
    }
}