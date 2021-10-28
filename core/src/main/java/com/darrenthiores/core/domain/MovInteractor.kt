package com.darrenthiores.core.domain

import com.darrenthiores.core.data.repository.IMovRepository
import com.darrenthiores.core.model.domain.UserDomain
import kotlinx.coroutines.flow.Flow

class MovInteractor(private val repository: IMovRepository):MovUseCase {

    override fun setUsername(username: String) {
        repository.setUsername(username)
    }

    override fun getUsername(): String = repository.getUsername()

    override fun getIsLogin(): Boolean = repository.getIsLogin()

    override fun setLogin(isLogin:Boolean){
        repository.setLogin(isLogin)
    }

}