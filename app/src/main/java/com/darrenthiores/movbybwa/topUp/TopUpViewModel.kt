package com.darrenthiores.movbybwa.topUp

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore

class TopUpViewModel(movUseCase: MovUseCase):ViewModel() {

    private val username = movUseCase.getUsername()
    private val fireStore = FirebaseFirestore.getInstance()
    private val collection = fireStore.collection("Users")

    fun updateBalance(balance:Double):Task<Void> = collection.document(username).update("balance", balance)

}