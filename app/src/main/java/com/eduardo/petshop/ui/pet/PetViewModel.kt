package com.eduardo.petshop.ui.pet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardo.petshop.domain.model.Animal
import com.eduardo.petshop.domain.repository.PetRepository
import com.eduardo.petshop.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val repository: PetRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow(PetState())
    val state = _state.asStateFlow()

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private fun sendEvent(event: UiEvent){
        viewModelScope.launch {
            _event.send(event)
        }
    }

    init {
        savedStateHandle.get<String>("id")?.let {
            val id = it.toInt()
            viewModelScope.launch {
                repository.getAnimalById(id)?.let { pet ->
                    _state.update { screenState ->
                        screenState.copy(
                            petId = pet.petId,
                            type = pet.type,
                            age = pet.age,
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: PetEvent) {
        when (event) {
            is PetEvent.typeChange -> {
                _state.update {
                    it.copy(
                        type = event.value
                    )
                }
            }
            is PetEvent.nameChange -> {
                _state.update {
                    it.copy(
                        name = event.value
                    )
                }
            }
            is PetEvent.ageChange -> {
                _state.update {
                    it.copy(
                        age = event.value
                    )
                }
            }

            PetEvent.NavigateBack -> sendEvent(UiEvent.NavigateBack)
            PetEvent.Save -> {
                viewModelScope.launch {
                    val state = state.value
                    val pet = Animal(
                        petId = state.petId,
                        name = state.name,
                        type = state.type,
                        age = state.age,
                    )
                    if (state.petId == null) {
                        repository.insertAnimal(pet)
                    } else {
                        repository.updateAnimal(pet)
                    }
                    sendEvent(UiEvent.NavigateBack)
                }
            }
            PetEvent.DeletePet -> {
                viewModelScope.launch {
                    val state = state.value
                    val pet = Animal(
                        petId = state.petId,
                        name = state.name,
                        type = state.type,
                        age = state.age,
                    )
                    repository.deleteAnimal(pet)
                }
                sendEvent(UiEvent.NavigateBack)
            }
        }
    }

}