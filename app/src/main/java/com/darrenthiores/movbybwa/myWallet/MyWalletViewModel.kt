package com.darrenthiores.movbybwa.myWallet

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MyWalletViewModel(movUseCase: MovUseCase):ViewModel() {

    private val username = movUseCase.getUsername()
    private val fireStore = FirebaseFirestore.getInstance()
    private val collection = fireStore.collection("Users")

    fun getBalance(): Task<DocumentSnapshot> = collection.document(username).get()

}