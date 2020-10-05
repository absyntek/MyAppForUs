package com.absyntek.myappforus.utils.firebase

import com.absyntek.myappforus.models.Message
import com.absyntek.myappforus.models.Product
import com.google.firebase.firestore.Query
import java.util.*

class MessageHelper(userId: String) {

    private val COLLECTION_NAME = "chat"
    private val chatCollection = UserHelper.usersCollection.document(userId).collection(COLLECTION_NAME)

    fun create(message: Message) = chatCollection.document().set(message)

    fun get(id: String) = chatCollection.document(id).get()

    fun getQuery() = chatCollection.orderBy("createdAt", Query.Direction.DESCENDING)

    fun update(id: String, message: Message) = chatCollection.document(id).set(message)


    fun delete(id: String) = chatCollection.document(id).delete()
}