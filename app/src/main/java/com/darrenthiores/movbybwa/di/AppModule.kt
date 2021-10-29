package com.darrenthiores.movbybwa.di

import com.darrenthiores.core.domain.MovInteractor
import com.darrenthiores.core.domain.MovUseCase
import com.darrenthiores.movbybwa.Photo.AddPhotoViewModel
import com.darrenthiores.movbybwa.SignIn.SignInViewModel
import com.darrenthiores.movbybwa.SignUp.SignUpViewModel
import com.darrenthiores.movbybwa.Splash.SplashViewModel
import com.darrenthiores.movbybwa.checkOut.CheckOutViewModel
import com.darrenthiores.movbybwa.detail.DetailViewModel
import com.darrenthiores.movbybwa.history.HistoryViewModel
import com.darrenthiores.movbybwa.home.HomeViewModel
import com.darrenthiores.movbybwa.editProfile.EditProfileViewModel
import com.darrenthiores.movbybwa.myWallet.MyWalletViewModel
import com.darrenthiores.movbybwa.profile.ProfileViewModel
import com.darrenthiores.movbybwa.ticket.TicketViewModel
import com.darrenthiores.movbybwa.topUp.TopUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovUseCase> { MovInteractor(get()) }
}

val viewModelModule = module {

    viewModel { SignInViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { AddPhotoViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel() }
    viewModel { CheckOutViewModel(get()) }
    viewModel { TicketViewModel() }
    viewModel { HistoryViewModel() }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
    viewModel { MyWalletViewModel(get()) }
    viewModel { TopUpViewModel(get()) }

}