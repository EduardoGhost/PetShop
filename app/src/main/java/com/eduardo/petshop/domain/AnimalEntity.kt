package com.eduardo.petshop.domain

data class AnimalEntity(
    val id: Int = 0,
    val name: String,
    val type: String,
    val age: Int,
    val picture: String,
    val gender: String,
)