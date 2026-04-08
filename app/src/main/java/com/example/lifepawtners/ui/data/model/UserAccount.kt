package com.example.lifepawtners.ui.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: String // "ADOPTER" or "LISTER"
)