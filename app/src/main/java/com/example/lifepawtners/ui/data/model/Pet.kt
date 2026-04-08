package com.example.lifepawtners.ui.data.model

import androidx.annotation.DrawableRes

// This represents ONE pet in your app
// data
data class Pet(
    val id: String,            // unique ID for the pet
    val ownerId: String,       // which account owns this pet
    val name: String,
    val age: Int,
    val type: String,          // Dog, Cat, etc.
    val breed: String,
    val bio: String,

    // This tells Android this is a drawable resource (image)
    @DrawableRes val imageRes: Int
)