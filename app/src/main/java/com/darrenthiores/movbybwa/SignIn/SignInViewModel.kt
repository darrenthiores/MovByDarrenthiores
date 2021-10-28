package com.darrenthiores.movbybwa.SignIn

import androidx.lifecycle.ViewModel
import com.darrenthiores.core.domain.MovUseCase
import com.darrenthiores.core.model.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class SignInViewModel(private val movUseCase: MovUseCase) : ViewModel(){

    private val collection = FirebaseFirestore.getInstance().collection("Users")

    fun setUser(username: String){
        movUseCase.setUsername(username)
    }

    fun setLogin(isLogin:Boolean){
        movUseCase.setLogin(isLogin)
    }

    fun checkUser(username: String): Task<DocumentSnapshot> = collection.document(username.lowercase()).get()

    fun checkPassword(data:DocumentSnapshot?, password:String):Boolean {
        val user = data?.toObject(User::class.java)
        return password == user?.password
    }

}