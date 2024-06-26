package com.eduardo.petshop.ui.pet

sealed interface PetEvent{
    data class nameChange(val value: String): PetEvent
    data class typeChange(val value: String): PetEvent
    data class ageChange(val value: Int): PetEvent
    data class pictureChange(val value: String): PetEvent
    object Save: PetEvent
    object NavigateBack : PetEvent
    object DeletePet : PetEvent

}