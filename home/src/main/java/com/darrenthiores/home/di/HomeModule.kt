package com.darrenthiores.home.di

import com.darrenthiores.movbybwa.checkOut.CheckOutViewModel
import com.darrenthiores.movbybwa.detail.DetailViewModel
import com.darrenthiores.movbybwa.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module{
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel() }
    viewModel { CheckOutViewModel(get()) }
}