package com.pn.aluminium.catalogue

import android.app.Application
import androidx.multidex.MultiDex

class MyApplication : Application() {
    companion object {
        var mInstance: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        MultiDex.install(this)
    }
}
