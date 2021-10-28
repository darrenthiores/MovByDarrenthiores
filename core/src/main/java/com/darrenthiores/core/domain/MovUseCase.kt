package com.darrenthiores.core.domain

import com.darrenthiores.core.model.domain.UserDomain
import kotlinx.coroutines.flow.Flow

interface MovUseCase {

    fun setUsername(username:String)

    fun getUsername():String

    fun getIsLogin():Boolean

    fun setLogin(isLogin:Boolean)

}