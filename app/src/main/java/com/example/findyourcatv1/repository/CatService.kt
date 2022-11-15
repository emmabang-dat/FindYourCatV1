package com.example.findyourcatv1.repository

import com.example.findyourcatv1.models.Cat
import retrofit2.Call
import retrofit2.http.*

interface CatService {
    @GET("cats")
    fun getAllCats(): Call<List<Cat>>

    @POST("cats")
    fun saveCat(@Body cat: Cat): Call<Cat>

    @DELETE("cats/{id}")
    fun deleteCat(@Path("id") id: Int): Call<Cat>

    @PUT("cats/{id}")
    fun updateCat(@Path("id") id: Int, @Body cat: Cat): Call<Cat>
}