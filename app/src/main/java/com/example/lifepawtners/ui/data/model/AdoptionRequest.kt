
package com.example.lifepawtners.ui.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adoption_requests")
data class AdoptionRequest(
    @PrimaryKey val requestId: String,
    val petId: String,
    val petName: String,
    val petBreed: String,
    val ownerEmail: String,
    val senderEmail: String,
    val senderName: String,
    val messageText: String
)