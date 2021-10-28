package com.darrenthiores.core.model.data

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @PropertyName("username")
    var username:String = "",
    @PropertyName("password")
    var password:String="",
    @PropertyName("name")
    var name:String="",
    @PropertyName("email")
    var email:String="",
    @PropertyName("saldo")
    var balance:Double=0.0,
    @PropertyName("url")
    var url:String=""
):Parcelable
