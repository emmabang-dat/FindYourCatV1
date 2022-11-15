package com.example.findyourcatv1.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.findyourcatv1.repository.CatRepository

class CatsViewModel : ViewModel() {
    private val repository = CatRepository()
    val catsLiveData: LiveData<List<Cat>> = repository.catsLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getPosts()
    }

    fun getSort(sort_by: String) {
        var data: List<Cat> = catsLiveData.value!!

        if (sort_by.isNotBlank()) {
            when (sort_by) {
                "name" -> data = catsLiveData.value!!.sortedBy { it.name }
                "place" -> data = catsLiveData.value!!.sortedBy { it.place }
                "reward" -> data = catsLiveData.value!!.sortedByDescending { it.reward }
                "date" -> data = catsLiveData.value!!.sortedByDescending { it.date }
                else -> {
                    Log.d("BANANA", "incorrect sorting parameter")
                }
            }
        }

        if (sort_by.isBlank()) {
            reload()
            return
        }

        repository.getSort(data)
    }

    fun filterByName(name: String) {
        repository.filterByName(name)
    }

    operator fun get(index: Int): Cat?{
        return catsLiveData.value?.get(index)
    }

    fun add(cat: Cat) {
        repository.add(cat)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun update(cat: Cat) {
        repository.update(cat)
    }
}