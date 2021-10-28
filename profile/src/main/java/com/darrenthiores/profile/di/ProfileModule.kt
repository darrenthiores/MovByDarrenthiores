package com.darrenthiores.profile.di

import com.darrenthiores.movbybwa.profile.EditProfileViewModel
import com.darrenthiores.movbybwa.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}