package com.darrenthiores.movbybwa.home

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class HomeViewModel(movUseCase: MovUseCase):ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    private val collection = fireStore.collection("Users")
    private val movieCollection = fireStore.collection("Movies")
    private val comingSoonCollection = fireStore.collection("ComingSoon")
    private val username = movUseCase.getUsername()

    fun getData(): Task<DocumentSnapshot> = collection.document(username).get()

    fun getMovies(): Task<QuerySnapshot> = movieCollection.get()

    fun getComingSoon():Task<QuerySnapshot> = comingSoonCollection.get()

}