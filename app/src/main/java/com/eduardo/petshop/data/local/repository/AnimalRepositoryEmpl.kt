package com.eduardo.petshop.data.local.repository

import com.eduardo.petshop.data.local.dao.AnimalDao
import com.eduardo.petshop.data.local.mapper.asExternalModel
import com.eduardo.petshop.data.local.mapper.toEntity
import com.eduardo.petshop.domain.model.Animal
import com.eduardo.petshop.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimalRepositoryEmpl(
    private val dao: AnimalDao
): PetRepository {

    override fun getAllPets(): Flow<List<Animal>> {
        return dao.getAllAnimals()
            .map { pets ->
                pets.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getAnimalById(id: Int): Animal? {
       return dao.getAnimalById(id)?.asExternalModel()
    }

    override suspend fun insertAnimal(animal: Animal) {
        dao.insertAnimal(animal.toEntity())
    }

    override suspend fun deleteAnimal(animal: Animal) {
        dao.deleteAnimal(animal.toEntity())
    }

    override suspend fun updateAnimal(animal: Animal) {
        dao.updateAnimal(animal.toEntity())
    }


}