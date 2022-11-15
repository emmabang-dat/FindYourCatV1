package com.example.findyourcatv1.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.findyourcatv1.repository.UsersRepository

open class UsersViewModel : ViewModel() {
    private val repository = UsersRepository()
    val userLiveData = repository.userLiveData
    val errorLiveData = repository.errorLiveData

    fun signInWithEmailAndPassword(email: String, password: String) {
        repository.signInWithEmailAndPassword(email, password)
    }

    fun createUserWithEmailAndPassword(email: String, password: String) {
        repository.createUserWithEmailAndPassword(email, password)
    }

    fun signOut() {
        repository.signOut()
    }
}