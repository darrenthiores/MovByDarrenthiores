package com.darrenthiores.movbybwa.ticket

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class TicketViewModel : ViewModel() {

    private val collection = FirebaseFirestore.getInstance().collection("Movies")

    fun getMovies(): Task<QuerySnapshot> = collection.get()

}