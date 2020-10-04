package com.absyntek.myappforus.utils.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


abstract class BaseHelper {
    protected abstract val COLLECTION_NAME: String
    private val db = Firebase.firestore
    protected val collection = db.collection(COLLECTION_NAME)
}