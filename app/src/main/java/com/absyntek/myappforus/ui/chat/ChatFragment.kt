package com.absyntek.myappforus.ui.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absyntek.myappforus.NavigatorActivity
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentChatBinding
import com.absyntek.myappforus.models.Message
import com.absyntek.myappforus.models.User
import com.absyntek.myappforus.utils.NavigatorDirectory
import com.absyntek.myappforus.utils.firebase.MessageHelper
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.util.*

class ChatFragment : BaseFragment(){

    private lateinit var uid: String
    companion object{
        fun create() = ChatFragment()
        fun create(uid:String)= ChatFragment().apply {
            this.uid = uid
        }
    }

    private lateinit var bind: FragmentChatBinding
    private lateinit var adapter: ChatAdapter
    private lateinit var helper: MessageHelper
    private var isAdmin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAdmin = appGlobals().isAdmin
        helper = MessageHelper(uid)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentChatBinding.inflate(layoutInflater,container,false)
        val option = FirestoreRecyclerOptions.Builder<Message>().setQuery(helper.getQuery(), Message::class.java).build()
        adapter = ChatAdapter(option , appGlobals().currentUser?.uid?: "")
        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        lm.stackFromEnd = false
        lm.reverseLayout = true
        bind.rvChat.layoutManager = lm
        bind.rvChat.adapter = adapter

        bind.btnSend.setOnClickListener {
            helper.create(
                Message(
                    bind.edtText.text.toString(),
                    appGlobals().currentUser?.uid?:"",
                    Date()
                )
            )
        }
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}