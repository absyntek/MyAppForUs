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
    var isAdmin: Boolean
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
            isAdmin = value?.isAdmin?: false
            this._currentUser = value
        }

    private var _isAdmin = false
    override var isAdmin: Boolean
        get() = _isAdmin
        set(value) {if (_isAdmin != value) _isAdmin = value }
}