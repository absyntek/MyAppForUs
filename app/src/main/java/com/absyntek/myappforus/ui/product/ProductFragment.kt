package com.absyntek.myappforus.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentProductBinding
import com.absyntek.myappforus.models.Product
import com.bumptech.glide.Glide

class ProductFragment : BaseFragment(){
    lateinit var product: Product
    companion object{
        fun create() = ProductFragment()
        fun create(product: Product) = ProductFragment().apply {
            this.product = product
        }
    }

    private lateinit var bind: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentProductBinding.inflate(inflater, container, false)
        Glide.with(bind.imageTop).load(product.pictureUrl).into(bind.imageTop)
        bind.tvTitle.text = product.name
        bind.tvDesc.text = product.content
        return bind.root
    }
}