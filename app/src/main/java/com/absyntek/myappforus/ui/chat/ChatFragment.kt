package com.absyntek.myappforus.ui.chat

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
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
import timber.log.Timber
import java.util.*

class ChatFragment : BaseFragment(){

    private lateinit var user: User
    companion object{
        fun create() = ChatFragment()
        fun create(user: User)= ChatFragment().apply {
            this.user = user
        }
    }

    private lateinit var bind: FragmentChatBinding
    private lateinit var adapter: ChatAdapter
    private lateinit var helper: MessageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = if (this::user.isInitialized) MessageHelper(user.uid)
        else MessageHelper(appGlobals().currentUid)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentChatBinding.inflate(layoutInflater,container,false)
        val option = FirestoreRecyclerOptions.Builder<Message>().setQuery(helper.getQuery(), Message::class.java).build()

        bind.tvTitle.text = if (this::user.isInitialized) "${user.userName} <-> Seller" else "Seller <-> ${appGlobals().currentUser?.userName}"
        adapter = ChatAdapter(option , appGlobals().currentUser?.uid?: "")
        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        //lm.stackFromEnd = true
        bind.tvTitle
        bind.rvChat.layoutManager = lm
        bind.rvChat.adapter = adapter

        bind.edtText.doAfterTextChanged {
            bind.btnSend.isEnabled = !it.isNullOrBlank()
        }
        bind.btnSend.setOnClickListener {
            helper.create(
                Message(
                    bind.edtText.text.toString(),
                    appGlobals().currentUser?.uid?:"",
                    Date()
                )
            ).addOnSuccessListener {
                bind.rvChat.smoothScrollToPosition(0)
                bind.edtText.text = Editable.Factory.getInstance().newEditable("")
            }.addOnFailureListener {
                Timber.e(it)
            }
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