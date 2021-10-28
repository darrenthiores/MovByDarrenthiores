package com.darrenthiores.movbybwa.editProfile

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class EditProfileViewModel(movUseCase: MovUseCase):ViewModel() {

    private val collection = FirebaseFirestore.getInstance().collection("Users")
    private val username = movUseCase.getUsername()

    private val storageReference = FirebaseStorage.getInstance().reference
    val ref = storageReference.child("images/${UUID.randomUUID()}")

    fun updateImage(image:String) : Task<Void> = collection.document(username).update("url", image)
    fun updateData(name: String, email: String, password: String):Task<Void> = collection.document(username).update(mapOf(
        "name" to name,
        "email" to email,
        "password" to password
    ))

}