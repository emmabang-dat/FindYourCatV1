package com.example.findyourcatv1.models

import java.text.DateFormat


data class Cat(
    val id: Int,
    val name: String,
    val description: String,
    val place: String,
    val reward: Int,
    val userId: String,
    val date: Long
) {

    constructor(name: String, description: String, place: String, reward: Int, userId: String) :
            this(1, name, description, place, reward, userId, System.currentTimeMillis()/1000)

    override fun toString(): String{
        return "$id, $name, $description, $place, $reward, $userId, $date"
    }

    fun humanDate(): String {
        return DateFormat.getDateInstance().format(date * 1000L)
    }
}