package com.eduardo.petshop.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class AnimalEntity(
    @PrimaryKey val petId: Int?,
    val name: String?,
    val type: String?,
    val age: String?,
    //implementar
//    val picture: String? = null,
//    val gender: String? = null,
)