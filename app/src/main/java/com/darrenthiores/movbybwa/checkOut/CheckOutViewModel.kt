package com.darrenthiores.movbybwa.checkOut

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class CheckOutViewModel(movUseCase: MovUseCase):ViewModel() {

    private val username = movUseCase.getUsername()
    private val userCollection = FirebaseFirestore.getInstance().collection("Users")

    fun getBalance(): Task<DocumentSnapshot> = userCollection.document(username).get()

    fun updateBalance(balance:Double) { viewModelScope.launch{
        userCollection.document(username).update("balance", balance)
    } }

}