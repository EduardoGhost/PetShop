package com.eduardo.petshop.domain.repository

import com.eduardo.petshop.domain.model.Animal
import kotlinx.coroutines.flow.Flow

interface PetRepository {

    fun getAllPets(): Flow<List<Animal>>

    suspend fun getAnimalById(id: Int): Animal?

    suspend fun insertAnimal(animal: Animal)

    suspend fun deleteAnimal(animal: Animal)

    suspend fun updateAnimal(animal: Animal)
}