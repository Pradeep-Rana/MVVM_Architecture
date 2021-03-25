package com.wipro.news.app

import android.app.Application
import androidx.multidex.MultiDex

class NewsAppApplication : Application() {
    companion object {
        var mInstance: NewsAppApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        MultiDex.install(this)
    }
}
