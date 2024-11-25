package com.hw.my2

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun GifImage(url: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = modifier
    )
}