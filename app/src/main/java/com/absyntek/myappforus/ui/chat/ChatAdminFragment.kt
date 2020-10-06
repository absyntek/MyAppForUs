package com.absyntek.myappforus.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.absyntek.myappforus.NavigatorActivity
import com.absyntek.myappforus.api.appGlobals
import com.absyntek.myappforus.base.BaseFragment
import com.absyntek.myappforus.databinding.FragmentChatAdminBinding
import com.absyntek.myappforus.models.User
import com.absyntek.myappforus.utils.NavigatorDirectory
import com.absyntek.myappforus.utils.firebase.UserHelper
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ChatAdminFragment :BaseFragment(){

    private lateinit var bind: FragmentChatAdminBinding
    private lateinit var adapter: ChatAdminAdapter
    private lateinit var helper: UserHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        helper = UserHelper
        val option = FirestoreRecyclerOptions.Builder<User>().setQuery(helper.allUsers(appGlobals().currentUid), User::class.java).build()
        adapter = ChatAdminAdapter(option, ::onItemclick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind= FragmentChatAdminBinding.inflate(inflater, container, false)
        bind.rvUsers.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        bind.rvUsers.adapter = adapter
        return bind.root
    }

    private fun onItemclick(user: User){
        startActivity(NavigatorActivity.newIntent(requireContext(), NavigatorDirectory.Chat(user.uid)))
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