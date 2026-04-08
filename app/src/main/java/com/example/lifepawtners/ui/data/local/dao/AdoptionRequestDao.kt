package com.example.lifepawtners.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifepawtners.ui.data.model.AdoptionRequest

@Dao
interface AdoptionRequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: AdoptionRequest)

    @Query("SELECT * FROM adoption_requests WHERE ownerEmail = :ownerEmail")
    suspend fun getRequestsForOwner(ownerEmail: String): List<AdoptionRequest>
}