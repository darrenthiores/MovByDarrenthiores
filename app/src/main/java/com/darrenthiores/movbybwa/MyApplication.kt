package com.darrenthiores.movbybwa

import android.app.Application
import com.darrenthiores.core.di.repositoryModule
import com.darrenthiores.movbybwa.di.useCaseModule
import com.darrenthiores.movbybwa.di.viewModelModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }

    }
}