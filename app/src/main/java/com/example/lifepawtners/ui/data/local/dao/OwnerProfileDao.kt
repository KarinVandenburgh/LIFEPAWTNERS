package com.example.lifepawtners.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifepawtners.ui.data.model.OwnerProfile

@Dao
interface OwnerProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwnerProfile(profile: OwnerProfile)

    @Query("SELECT * FROM owner_profiles WHERE accountEmail = :email LIMIT 1")
    suspend fun getOwnerProfile(email: String): OwnerProfile?
}