package com.darrenthiores.core.model.data

import com.google.firebase.firestore.PropertyName

data class Cast(
    @PropertyName("name")
    var name:String = "",
    @PropertyName("photo")
    var photo:String = ""
)
