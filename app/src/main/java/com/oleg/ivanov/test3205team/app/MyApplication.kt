package com.oleg.ivanov.test3205team.app

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import com.oleg.ivanov.test3205team.di.AppModule
import com.oleg.ivanov.test3205team.di.ApplicationComponent
import com.oleg.ivanov.test3205team.di.DaggerApplicationComponent
import com.oleg.ivanov.data.di.RoomModule

class MyApplication : Application(), DefaultLifecycleObserver {

    companion object {
        lateinit var appComponent: ApplicationComponent
        lateinit var instance: MyApplication private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super<Application>.onCreate()

        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        appComponent = DaggerApplicationComponent
            .builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }

}