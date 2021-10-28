package com.darrenthiores.core.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckOut(
    var seat:String = "",
    var price:Int = 0
) : Parcelable
