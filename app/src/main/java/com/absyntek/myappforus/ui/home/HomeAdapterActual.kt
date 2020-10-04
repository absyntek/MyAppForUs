package com.absyntek.myappforus.ui.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.absyntek.myappforus.base.BaseViewHolder
import com.absyntek.myappforus.databinding.FragentHomeProductItemBinding
import com.absyntek.myappforus.models.Product
import com.absyntek.myappforus.utils.ui.PimpMyToast
import com.absyntek.myappforus.utils.ui.ensureGone
import com.absyntek.myappforus.utils.ui.ensureVisible
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import java.util.*

class HomeAdapterActual(options: FirestoreRecyclerOptions<Product>) : FirestoreRecyclerAdapter<Product, BaseViewHolder<FragentHomeProductItemBinding>>(options){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FragentHomeProductItemBinding> {
        context = parent.context
        val bind = FragentHomeProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return BaseViewHolder(bind)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FragentHomeProductItemBinding>, position: Int, model: Product) {
        if (model.quantity.toInt() == 0) holder.binding.root.ensureGone()
        else{
            holder.binding.root.ensureVisible()
            val bind = holder.binding
            Glide.with(bind.imgProduct).load(model.pictureUrl).into(bind.imgProduct)
            bind.tvTitle.text = model.name
            if (Date().before(model.timeStart)){
                bind.layoutDate.ensureVisible()
                bind.layoutQuantity.ensureGone()
            }else{
                bind.layoutDate.ensureGone()
                bind.layoutQuantity.ensureVisible()
                bind.progressBarQuantity.progress = model.quantity.toInt()

                bind.progressBarQuantity.progressDrawable.colorFilter =  when (model.quantity.toInt()){
                    1 , 19 ->{ BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.RED, BlendModeCompat.SRC_ATOP) }
                    20 , 49 ->{ BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.YELLOW, BlendModeCompat.SRC_ATOP) }
                    50 , 100 ->{ BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GREEN, BlendModeCompat.SRC_ATOP) }
                    else -> { BlendModeColorFilterCompat.createBlendModeColorFilterCompat(Color.GREEN, BlendModeCompat.SRC_ATOP) }
                }
                bind.tvIndica.text = "Indica : ${model.indica}"
                bind.tvSativa.text = "Sativa : ${model.sativa}"
            }
        }
    }

    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        e?.message?.let {
            PimpMyToast(context).redTopToast(it)
        }
    }

}