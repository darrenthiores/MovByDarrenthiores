package com.darrenthiores.movbybwa.profile

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.darrenthiores.core.model.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking

class ProfileViewModel(movUseCase: MovUseCase):ViewModel() {

    private val username = movUseCase.getUsername()
    private val fireStore = FirebaseFirestore.getInstance()
    private val collection = fireStore.collection("Users")

    fun getProfile(): Task<DocumentSnapshot> = collection.document(username).get()

}