package com.eduardo.petshop.ui.pet

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.eduardo.petshop.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetScreen(
    state: PetState,
    onEvent: (PetEvent) -> Unit,
    onImageSelected: (Uri) -> Unit
) {
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                Log.d("URI_DEBUG", "URI: $uri")
                onImageSelected(it)
            }
        }
    )
    var imageUri by remember { mutableStateOf<Uri?>(null) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(PetEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(PetEvent.DeletePet)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = state.name ?: "",
                onValueChange = {
                    onEvent(PetEvent.nameChange(it))
                },
                placeholder = {
                    Text(text = "Name", color = Gray)
                }
            )
            OutlinedTextField(
                value = state.type ?: "",
                onValueChange = {
                    onEvent(PetEvent.typeChange(it))
                },
                placeholder = {
                    Text(text = "Type", color = Gray)
                }
            )

            OutlinedTextField(
                value = state.age ?: "",
                onValueChange = {
                    onEvent(PetEvent.ageChange(it))
                },
                placeholder = {
                    Text(text = "Age", color = Gray)
                }
            )

            Button(
                onClick = {
                    galleryLauncher.launch("image/*")
                }
            ) {
                Text(
                    text = "Pick image"
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(36.dp)
                    )
                }

                Button(
                    onClick = {
                        onEvent(PetEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PetScreenPreview() {
//    val state = PetState(name = "jao", type = "Dog", age = "3", picture = "")
//    PetShopTheme {
//        PetScreen(state = state, onEvent = {})
//    }
//}