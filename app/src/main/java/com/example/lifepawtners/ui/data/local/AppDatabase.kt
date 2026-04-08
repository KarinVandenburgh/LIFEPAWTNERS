package com.example.lifepawtners.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lifepawtners.ui.data.local.dao.AdoptionRequestDao
import com.example.lifepawtners.ui.data.local.dao.OwnerProfileDao
import com.example.lifepawtners.ui.data.local.dao.PetProfileDao
import com.example.lifepawtners.ui.data.local.dao.UserAccountDao
import com.example.lifepawtners.ui.data.model.AdoptionRequest
import com.example.lifepawtners.ui.data.model.OwnerProfile
import com.example.lifepawtners.ui.data.model.PetProfile
import com.example.lifepawtners.ui.data.model.UserAccount

@Database(
    entities = [
        UserAccount::class,
        OwnerProfile::class,
        PetProfile::class,
        AdoptionRequest::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userAccountDao(): UserAccountDao
    abstract fun ownerProfileDao(): OwnerProfileDao
    abstract fun petProfileDao(): PetProfileDao
    abstract fun adoptionRequestDao(): AdoptionRequestDao
}