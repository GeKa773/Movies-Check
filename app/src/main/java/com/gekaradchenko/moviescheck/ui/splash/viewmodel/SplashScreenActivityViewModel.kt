package com.gekaradchenko.moviescheck.ui.splash.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.gekaradchenko.moviescheck.helper.SingleLiveEvent

class SplashScreenActivityViewModel() :ViewModel() {

    private val _go = MutableLiveData<Boolean>()
    val go: LiveData<Boolean> = _go

    private val _navigationEvent = SingleLiveEvent<Boolean>()
    val navigationEvent: LiveData<Boolean> = _navigationEvent



    fun onNavigate(){
        _navigationEvent.postValue(true)
    }


}