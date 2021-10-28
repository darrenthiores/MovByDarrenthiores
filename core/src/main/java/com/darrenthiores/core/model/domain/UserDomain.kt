package com.darrenthiores.core.model.domain

data class UserDomain(
    var username:String? = null,
    var password:String?=null,
    var name:String?=null,
    var email:String?=null,
    var balance:Double?=null,
    var url:String?=null
)
