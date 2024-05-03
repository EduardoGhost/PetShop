package com.eduardo.petshop.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

//corrigir e usar
@Composable
fun SelectImageFromGallery(onImageSelected: (Uri) -> Unit) {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val data: Intent? = result.data
        }
    }

    val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        type = "image/*"
    }
}

