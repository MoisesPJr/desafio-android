package com.picpay.desafio.android

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication(){


    override fun onCreate() {
        super.onCreate()


    }
}