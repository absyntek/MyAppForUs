package com.absyntek.myappforus.ui.parametter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.absyntek.myappforus.base.BaseViewModel

class NotificationsViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}