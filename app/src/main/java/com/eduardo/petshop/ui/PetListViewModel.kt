package com.eduardo.petshop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardo.petshop.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PetListViewModel @Inject constructor(
    private val repository: PetRepository): ViewModel(){
 val petList = repository.getAllPets().stateIn(
     scope = viewModelScope,
     started = SharingStarted.WhileSubscribed(5000),
     initialValue = emptyList()
 )
}