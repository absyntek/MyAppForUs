package com.absyntek.myappforus.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.absyntek.myappforus.base.BaseViewHolder
import com.absyntek.myappforus.databinding.FragmentChatUserItemBinding
import com.absyntek.myappforus.models.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ChatAdminAdapter(options: FirestoreRecyclerOptions<User>, val onItemClick: (User) -> Unit) : FirestoreRecyclerAdapter<User, BaseViewHolder<FragmentChatUserItemBinding>>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FragmentChatUserItemBinding> {
        val bind = FragmentChatUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(bind)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FragmentChatUserItemBinding>, position: Int, model: User) {
        holder.binding.tvName.text = model.userName
        holder.binding.root.setOnClickListener {
            onItemClick(model)
        }
    }

}