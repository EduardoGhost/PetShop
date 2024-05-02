package com.eduardo.petshop.data.local.mapper

import com.eduardo.petshop.data.local.entity.AnimalEntity
import com.eduardo.petshop.domain.model.Animal

fun AnimalEntity.asExternalModel(): Animal = Animal(
    petId, name, type, age
)
fun Animal.toEntity(): AnimalEntity = AnimalEntity(
    petId, name, type, age
)