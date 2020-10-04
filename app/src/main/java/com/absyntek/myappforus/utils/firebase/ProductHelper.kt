package com.absyntek.myappforus.utils.firebase

import com.absyntek.myappforus.models.Product
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

object ProductHelper{
    private const val COLLECTION_NAME = "products"
    private val db = Firebase.firestore
    private val collection = db.collection(COLLECTION_NAME)

    fun create(product: Product) = collection.document().set(product)

    fun get(id: String) = collection.document(id).get()
    fun queryActual() = collection.whereLessThanOrEqualTo("timeStart", Date())
    fun queryNext() = collection.whereGreaterThan("timeStart", Date())

    fun update(id: String, product: Product) = collection.document(id).set(product)

    fun updateDate(id: String, product: Product) = collection.document(id).update("timeStart", product.timeStart)
    fun updateQuantity(id: String, product: Product) = collection.document(id).update("quantity", product.quantity)
    fun updatePictureUrl(id: String, product: Product) = collection.document(id).update("pictureUrl", product.pictureUrl)

    fun delete(id: String) = collection.document(id).delete()

}