package com.hw.my2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun GifImage(url: String) {
    var isLoading by remember { mutableStateOf(true) } // Локальное состояние для управления загрузкой

    Box(
        modifier = Modifier
            .fillMaxWidth() // Растягиваем по ширине
            .aspectRatio(1.78f) // Примерное соотношение сторон (можно подстроить под ваши изображения)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit, // Сохраняем пропорции изображения
            onState = { state ->
                isLoading = state is AsyncImagePainter.State.Loading
            }
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

