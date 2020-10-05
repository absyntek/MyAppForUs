package com.absyntek.myappforus.utils.firebase

import com.absyntek.myappforus.models.User
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


object UserHelper {
    private const val COLLECTION_NAME = "users"
    private val db = Firebase.firestore

    // --- COLLECTION REFERENCE ---
    val usersCollection = db.collection(COLLECTION_NAME)

    // --- CREATE ---
    fun createUser(userToCreate: User) = usersCollection.document(userToCreate.uid).set(userToCreate)

    // --- GET ---
    val users: Query
        get() = usersCollection.orderBy("username", Query.Direction.ASCENDING)

    fun getUsersSearch(search: String): Query {
        return usersCollection.whereGreaterThanOrEqualTo("username", search)
            .whereLessThanOrEqualTo("username", search + "z")
    }

    fun getUser(uid: String) = usersCollection.document(uid).get()


    // --- UPDATE ---
    fun updateUsername(username: String, uid: String)=  usersCollection.document(uid).update("username", username)


    // --- DELETE ---
    fun deleteUser(uid: String) = usersCollection.document(uid).delete()

}