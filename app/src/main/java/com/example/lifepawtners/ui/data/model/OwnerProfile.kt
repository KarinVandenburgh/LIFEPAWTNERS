package com.example.lifepawtners.ui.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "owner_profiles",
    foreignKeys = [
        ForeignKey(
            entity = UserAccount::class,
            parentColumns = ["email"],
            childColumns = ["accountEmail"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("accountEmail")]
)
data class OwnerProfile(
    @PrimaryKey val accountEmail: String,
    val age: String,
    val housingType: String,
    val hasYard: String,
    val hasPets: String,
    //val bio: String,
    val photoUri: String? = null
)