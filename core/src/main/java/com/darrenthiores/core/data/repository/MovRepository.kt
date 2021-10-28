package com.darrenthiores.core.data.repository

import com.darrenthiores.core.data.dataStore.DataPreferences
import com.darrenthiores.core.model.domain.UserDomain
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovRepository(
    private val preferences: DataPreferences
) : IMovRepository{

    override fun setUsername(username: String) {
        preferences.setUser(username)
    }

    override fun getUsername(): String = preferences.getUsername()

    override fun getIsLogin(): Boolean = preferences.getLogin()

    override fun setLogin(isLogin: Boolean) {
        preferences.setLogin(isLogin)
    }

}