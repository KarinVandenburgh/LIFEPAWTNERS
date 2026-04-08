package com.example.lifepawtners.ui.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifepawtners.ui.data.model.PetProfile


@Dao
interface PetProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPetProfile(pet: PetProfile)

    @Query("SELECT * FROM pet_profiles WHERE ownerEmail = :email")
    suspend fun getPetsForOwner(email: String): List<PetProfile>

    @Query("SELECT * FROM pet_profiles")
    suspend fun getAllPets(): List<PetProfile>
}