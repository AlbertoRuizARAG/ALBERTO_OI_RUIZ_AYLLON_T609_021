package com.prilux.playgatewayandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prilux.playgatewayandroid.BuildConfig
import global.login.LoginGlobalCredentials

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val versionCode = BuildConfig.VERSION_CODE
        val versionName = BuildConfig.VERSION_NAME

        value = """${LoginGlobalCredentials.user.tenant}
            ${LoginGlobalCredentials.user.user}
            
            ${versionName} - V${versionCode}"""
    }
    val text: LiveData<String> = _text


}