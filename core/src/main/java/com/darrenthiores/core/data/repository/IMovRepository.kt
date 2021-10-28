package com.darrenthiores.core.data.repository

import com.darrenthiores.core.model.domain.UserDomain
import kotlinx.coroutines.flow.Flow

interface IMovRepository {

    fun setUsername(username:String)

    fun getUsername():String

    fun getIsLogin():Boolean

    fun setLogin(isLogin:Boolean)

}