package com.absyntek.myappforus.utils

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.absyntek.myappforus.models.Product
import com.absyntek.myappforus.ui.chat.ChatFragment
import com.absyntek.myappforus.ui.create_user.CreateUserFragment
import com.absyntek.myappforus.ui.product.ProductFragment
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

sealed class NavigatorDirectory : Serializable{
    open fun toFragment(): Fragment? = null


    object CreateUser: NavigatorDirectory(){
        override fun toFragment() = CreateUserFragment()
    }

    @Parcelize
    class ProductFr(val product: Product) : NavigatorDirectory(), Parcelable {
        override fun toFragment() = ProductFragment.create(product)
    }

    object Chat: NavigatorDirectory(){
        override fun toFragment() = ChatFragment.create()
    }
}