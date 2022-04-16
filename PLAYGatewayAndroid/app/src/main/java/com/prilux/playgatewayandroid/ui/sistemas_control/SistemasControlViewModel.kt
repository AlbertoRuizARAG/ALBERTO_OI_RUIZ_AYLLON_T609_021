package com.prilux.playgatewayandroid.ui.sistemas_control

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SistemasControlViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Sistemas De control" +
                "CMR" +
                "PLC" +
                "AIOTI"
    }
    val text: LiveData<String> = _text
}