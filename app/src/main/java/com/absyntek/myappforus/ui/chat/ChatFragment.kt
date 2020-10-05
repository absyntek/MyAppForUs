package com.absyntek.myappforus.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentChatAdminBinding
import com.absyntek.myappforus.databinding.FragmentChatUserBinding

class ChatFragment : BaseFragment(){

    companion object{
        fun create() = ChatFragment()
    }

    private lateinit var bind: ViewBinding
    private var isAdmin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAdmin = appGlobals().isAdmin
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = if (isAdmin){
            FragmentChatAdminBinding.inflate(layoutInflater, container, false)
        }else {
            FragmentChatUserBinding.inflate(layoutInflater,container,false)
        }
        return bind.root
    }
}