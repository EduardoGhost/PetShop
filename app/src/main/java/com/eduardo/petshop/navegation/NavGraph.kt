package com.eduardo.petshop.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eduardo.petshop.domain.model.Animal
import com.eduardo.petshop.ui.PetListScreen
import com.eduardo.petshop.ui.SplashScreen


@Composable
fun NavGraph(
    startDestination: String = "splash_screen",
    petList: List<Animal>,
    onPetClick: (Animal) -> Unit,
    onAddPetClick: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "splash_screen") { SplashScreen(navController) }
        composable(route = "main_screen") {
            PetListScreen(
                petList = petList,
                onPetClick = onPetClick,
                onAddPetClick = onAddPetClick
            )
        }
        // outras telas...
    }
}
