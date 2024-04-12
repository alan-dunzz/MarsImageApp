package com.example.randomimageapp.viewmodel

import android.app.Application
import com.example.randomimageapp.data.AppContainer
import com.example.randomimageapp.data.DefaultAppContainer

class MarsPhotoApplication:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=DefaultAppContainer()
    }
}