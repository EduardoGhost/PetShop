package com.eduardo.petshop.data.local.dao

import androidx.room.*
import com.eduardo.petshop.data.local.entity.AnimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: AnimalEntity)

    @Update
    suspend fun updateAnimal(animal: AnimalEntity)

    @Delete
    suspend fun deleteAnimal(animal: AnimalEntity)

    @Query("SELECT * FROM AnimalEntity")
    fun getAllAnimals(): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM AnimalEntity WHERE petId = :animalId")
    suspend fun getAnimalById(animalId: Int): AnimalEntity?
}