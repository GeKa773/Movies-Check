package com.gekaradchenko.moviescheck.ui.splash.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenActivityViewModel() :ViewModel() {

    private val _go = MutableLiveData<Boolean>()
    val go: LiveData<Boolean> = _go
    fun goNext() {
        _go.value = true
    }


}