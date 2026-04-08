package com.example.lifepawtners.ui.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pet_profiles",
    foreignKeys = [
        ForeignKey(
            entity = UserAccount::class,
            parentColumns = ["email"],
            childColumns = ["ownerEmail"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ownerEmail")]
)
data class PetProfile(
    @PrimaryKey val petId: String,
    val ownerEmail: String,
    val name: String,
    val age: String,
    val type: String,
    val breed: String,
    //val bio: String = "",
    val photoUri: String? = null
)