package com.absyntek.myappforus.utils

import androidx.fragment.app.Fragment
import com.absyntek.myappforus.ui.create_user.CreateUserFragment
import java.io.Serializable

sealed class NavigatorDirectory : Serializable{
    open fun toFragment(): Fragment? = null


    object CreateUser: NavigatorDirectory(){
        override fun toFragment() = CreateUserFragment()
    }
}