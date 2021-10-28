package com.darrenthiores.core.utils

import com.darrenthiores.core.model.data.User
import com.darrenthiores.core.model.domain.UserDomain

object DataMapper {

    fun mapEntityToDomain(data:User?):UserDomain = UserDomain(
        data?.username,
        data?.password,
        data?.name,
        data?.email,
        data?.balance,
        data?.url
    )

}