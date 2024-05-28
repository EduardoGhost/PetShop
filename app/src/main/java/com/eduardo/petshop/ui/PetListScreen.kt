package com.eduardo.petshop.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Text
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eduardo.petshop.domain.model.Animal
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListScreen(
    petList: List<Animal>,
    onPetClick: (Animal) -> Unit,
    onAddPetClick: () -> Unit,
){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPetClick)
            {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add pet")
            }
        }
    ){ padding ->
        LazyColumn(contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
            top = 15.dp + padding.calculateTopPadding(),
            bottom = 15.dp + padding.calculateBottomPadding()
        )) {
            item {
                Text(
                    text = "PetShop - Registro de Animais",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(petList) { pet ->
                ListItem({
                    Column {
                        Text(
                            text = "Nome",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = pet.name ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Tipo",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = pet.type ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Idade",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = pet.age ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Foto",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        AsyncImage(model = pet.picture,
                            contentDescription = "imagem")
                    }
                },
                    modifier = Modifier.clickable { onPetClick(pet) }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PetScreenPreview() {
//    val state = PetState(name = "Dogão", type = "Dog", age = "3", picture = "content://com.android.externalstorage.documents/document/primary%3ADownload%2Fcao.jpg")
//
//    PetShopTheme {
//        PetScreen(
//            state = state,
//            onEvent = { /* Handle events */ }
//        )
//    }
//}