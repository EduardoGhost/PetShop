package com.eduardo.petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardo.petshop.ui.PetListScreen
import com.eduardo.petshop.ui.PetListViewModel
import com.eduardo.petshop.ui.pet.PetScreen
import com.eduardo.petshop.ui.pet.PetViewModel
import com.eduardo.petshop.ui.theme.PetShopTheme
import com.eduardo.petshop.ui.util.Route
import com.eduardo.petshop.ui.util.UiEvent
import androidx.hilt.navigation.compose.hiltViewModel
import com.eduardo.petshop.ui.SplashScreen
import com.eduardo.petshop.ui.pet.PetEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetShopTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.splashScreen
                ) {
                    composable(route = Route.splashScreen) {
                        SplashScreen(navController)
                    }

                    composable(route = Route.petList) {
                        val viewModel = hiltViewModel<PetListViewModel>()
                        val petList by viewModel.petList.collectAsStateWithLifecycle()

                        PetListScreen(
                            petList = petList,
                            onPetClick = {
                                navController.navigate(
                                    Route.pet.replace(
                                        "{id}",
                                        it.petId.toString()
                                    )
                                )
                            },
                            onAddPetClick = {
                                navController.navigate(Route.pet)
                            }
                        )
                    }

                    composable(route = Route.pet) {
                        val viewModel = hiltViewModel<PetViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect { event ->
                                when (event) {
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }

                                    else -> Unit
                                }
                            }
                        }

                        PetScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                            onImageSelected = { uri ->
                                viewModel.onEvent(PetEvent.pictureChange(uri.toString()))
                            }
                        )
                    }
                }
            }
        }
    }
}