package com.darrenthiores.core.di

import com.darrenthiores.core.data.dataStore.DataPreferences
import com.darrenthiores.core.data.repository.IMovRepository
import com.darrenthiores.core.data.repository.MovRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { DataPreferences(androidContext()) }
    single<IMovRepository> { MovRepository(get()) }
}