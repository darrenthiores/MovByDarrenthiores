package com.darrenthiores.movbybwa.SignUp

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.darrenthiores.core.model.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel(private val movUseCase: MovUseCase):ViewModel() {

    private val collection = FirebaseFirestore.getInstance().collection("Users")

    fun setUsername(username: String){
        movUseCase.setUsername(username)
    }

    fun setLogin(isLogin:Boolean){
        movUseCase.setLogin(isLogin)
    }

    fun checkUser(username: String): Task<DocumentSnapshot> = collection.document(username.lowercase()).get()

    fun setUser(user:User) : Task<Void> = collection.document(user.username.lowercase()).set(user)

}