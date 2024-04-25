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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eduardo.petshop.domain.model.Animal
import com.eduardo.petshop.ui.theme.PetShopTheme

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
                    text = "Pets",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(petList) { pet ->
                ListItem(
                    headlineText = {
                        pet.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    },
                    supportingText = {
                        pet.type?.let {
                            Text(
                                text = it,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    modifier = Modifier.clickable { onPetClick(pet) }
                )
            }
        }
    }
}







@Composable
fun Greeting2(name: String) {
    Text(text = "Hecllo $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PetShopTheme {
        Greeting2("Android")
    }
}