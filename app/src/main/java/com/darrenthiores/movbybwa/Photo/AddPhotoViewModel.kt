package com.darrenthiores.movbybwa.Photo

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddPhotoViewModel(movUseCase: MovUseCase):ViewModel() {

    private val collection = FirebaseFirestore.getInstance().collection("Users")
    private val username = movUseCase.getUsername()

    private val storageReference = FirebaseStorage.getInstance().reference
    val ref = storageReference.child("images/${UUID.randomUUID()}")

    fun updateData(image:String) : Task<Void> = collection.document(username).update("url", image)

    fun getName() : Task<DocumentSnapshot> = collection.document(username).get()

}