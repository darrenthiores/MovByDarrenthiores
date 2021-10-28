package com.darrenthiores.movbybwa.detail

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class DetailViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val movieCollection = fireStore.collection("Movies")
    private val comingSoonCollection = fireStore.collection("ComingSoon")

    fun getMovieCast(id:String):Task<QuerySnapshot> = movieCollection.document(id).collection("cast").get()

    fun getComingSoonCast(id:String):Task<QuerySnapshot> = comingSoonCollection.document(id).collection("cast").get()

}