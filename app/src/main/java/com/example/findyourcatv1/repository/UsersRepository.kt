package com.example.findyourcatv1.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UsersRepository {
    private var auth = Firebase.auth
    val userLiveData: MutableLiveData<FirebaseUser?> = MutableLiveData<FirebaseUser?>()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData<String?>()

    init {
        userLiveData.value = auth.currentUser
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userLiveData.value = auth.currentUser
            } else {
                errorLiveData.value = task.exception?.message.toString()
            }
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userLiveData.value = auth.currentUser
            } else {
                errorLiveData.value = task.exception?.message
            }
        }
    }

    fun signOut() {
        auth.signOut()
        userLiveData.value = auth.currentUser
    }
}