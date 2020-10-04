package com.absyntek.myappforus.utils

import androidx.fragment.app.Fragment
import com.absyntek.myappforus.models.Product
import com.absyntek.myappforus.ui.create_user.CreateUserFragment
import com.absyntek.myappforus.ui.product.ProductFragment
import java.io.Serializable

sealed class NavigatorDirectory : Serializable{
    open fun toFragment(): Fragment? = null


    object CreateUser: NavigatorDirectory(){
        override fun toFragment() = CreateUserFragment()
    }

    class ProductFr(val product: Product) : NavigatorDirectory() {
        override fun toFragment() = ProductFragment.create(product)
    }
}