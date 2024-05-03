package com.eduardo.petshop.domain.model

data class Animal(
    val petId: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val age: String? = null,
    //imple. imagem local e remoto
    val picture: String? = null,
//    val gender: String? = null,
)