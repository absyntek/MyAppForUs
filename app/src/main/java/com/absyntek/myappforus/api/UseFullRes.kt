package com.absyntek.myappforus.api

import com.absyntek.myappforus.models.User
import com.google.firebase.auth.FirebaseUser

fun appGlobals() = ServiceLocator.get()

object ServiceLocator {

    private lateinit var provider: Provider

    @JvmStatic
    fun get() = provider

    @JvmStatic
    fun initialize(provider: Provider) {
        this.provider = provider
    }
}

interface Provider {
    var firebaseUser: FirebaseUser?
    var currentUser: User?
    var admin: Boolean
    var currentUid: String
}

class UseFullRes : Provider{

    private var _firebaseUser: FirebaseUser? = null
    override var firebaseUser: FirebaseUser?
        get() = _firebaseUser
        set(value) {if (_firebaseUser != value) this._firebaseUser = value}

    private var _currentUser: User? = null
    override var currentUser: User?
        get() = _currentUser
        set(value) { if (_currentUser != value)
            admin = value?.admin?: false
            currentUid = value?.uid?: ""
            this._currentUser = value
        }

    private var _admin = false
    override var admin: Boolean
        get() = _admin
        set(value) {if (_admin != value) _admin = value }

    private var _currentUid = ""
    override var currentUid: String
        get() = _currentUid
        set(value) { if (_currentUid != value) _currentUid = value}
}