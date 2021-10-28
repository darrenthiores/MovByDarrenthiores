package com.darrenthiores.core.model.data

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @PropertyName("id")
    var id:String = "",
    @PropertyName("title")
    var title:String = "",
    @PropertyName("description")
    var description:String = "",
    @PropertyName("genre")
    var genre:String = "",
    @PropertyName("poster")
    var poster:String = "",
    @PropertyName("director")
    var director:String = "",
    @PropertyName("rating")
    var rating:Double = 0.0
) : Parcelable
