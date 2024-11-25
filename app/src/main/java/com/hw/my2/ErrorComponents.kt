package com.hw.my2


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorPlaceholder(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ошибка загрузки данных")
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}

@Composable
fun RetryLoadMoreItem(onRetry: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Ошибка догрузки данных")
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onRetry) {
            Text("Повторить")
        }
    }
}