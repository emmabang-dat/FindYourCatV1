package com.example.findyourcatv1.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.findyourcatv1.models.Cat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatRepository {
    private val url = "https://anbo-restlostcats.azurewebsites.net/api/"

    private val catService: CatService
    val catsLiveData: MutableLiveData<List<Cat>> = MutableLiveData<List<Cat>>()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    val updateMessageLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        catService = build.create(CatService::class.java)
        getPosts()
    }

    fun getPosts() {
        catService.getAllCats().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                if (response.isSuccessful) {
                    val b: List<Cat>? = response.body()
                    catsLiveData.postValue(b!!)
                    errorMessageLiveData.postValue("")
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("BANANA", message)
                }
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("BANANA", t.message!!)
            }
        })
    }

    fun getSort(list: List<Cat>) {
        if (list.isNotEmpty()) {
            catsLiveData.postValue(list)
        } else {
            val message = "Error in getSort"
            errorMessageLiveData.postValue(message)
            Log.d("BANANA", message)
        }
    }

    fun filterByName(name: String) {
        if (name.isBlank()) {
            getPosts()
        } else {
            catsLiveData.value = catsLiveData.value?.filter { cat ->  cat.name.contains(name)}
        }
    }

    fun add(cat: Cat) {
        catService.saveCat(cat).enqueue(object : Callback<Cat> {
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                if (response.isSuccessful) {
                    Log.d("BANANA", "Added: " + response.body())
                    updateMessageLiveData.postValue("Added: " + response.body())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("BANANA", message)
                }
            }

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("BANANA", t.message!!)
            }
        })
    }

    fun delete(id: Int) {
        catService.deleteCat(id).enqueue(object : Callback<Cat> {
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                if (response.isSuccessful) {
                    Log.d("BANANA", "Updated: " + response.body())
                    updateMessageLiveData.postValue("Deleted: " + response.body())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("BANANA", message)
                }
            }

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("BANANA", t.message!!)
            }
        })
    }

    fun update(cat: Cat){
        catService.updateCat(cat.id, cat).enqueue(object : Callback<Cat> {
            override fun onResponse(call: Call<Cat>, response: Response<Cat>) {
                if (response.isSuccessful) {
                    Log.d("BANANA", "Updated: " + response.body())
                    updateMessageLiveData.postValue("Updated: " + response.body())
                } else {
                    val message = response.code().toString() + " " + response.message()
                    errorMessageLiveData.postValue(message)
                    Log.d("BANANA", message)
                }
            }

            override fun onFailure(call: Call<Cat>, t: Throwable) {
                errorMessageLiveData.postValue(t.message)
                Log.d("BANANA", t.message!!)
            }
        })
    }
}