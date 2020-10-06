package com.absyntek.myappforus.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.absyntek.myappforus.base.BaseViewHolder
import com.absyntek.myappforus.databinding.FragmentChatItemBinding
import com.absyntek.myappforus.models.Message
import com.absyntek.myappforus.utils.ui.ensureGone
import com.absyntek.myappforus.utils.ui.ensureVisible
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ChatAdapter(
    options: FirestoreRecyclerOptions<Message>,
    private val sender: String
) : FirestoreRecyclerAdapter<Message, BaseViewHolder<FragmentChatItemBinding>>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FragmentChatItemBinding> {
        val bind = FragmentChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(bind)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FragmentChatItemBinding>, position: Int, model: Message) {
        if (sender == model.sender){
            holder.binding.layoutReceiver.ensureGone()
            holder.binding.layoutSender.ensureVisible()
            holder.binding.tvSender.text = model.text
        }else{
            holder.binding.layoutReceiver.ensureVisible()
            holder.binding.layoutSender.ensureGone()
            holder.binding.tvReceiver.text = model.text
        }
    }

}