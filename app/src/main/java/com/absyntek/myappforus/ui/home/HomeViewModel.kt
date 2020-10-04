package com.absyntek.myappforus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.absyntek.myappforus.base.BaseViewModel
import com.absyntek.myappforus.models.Product
import com.absyntek.myappforus.utils.firebase.ProductHelper
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

}