package com.darrenthiores.movbybwa.Splash

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase

class SplashViewModel(movUseCase: MovUseCase):ViewModel() {

    val isLogin = movUseCase.getIsLogin()

}