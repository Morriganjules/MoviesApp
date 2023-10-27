package com.example.moviesapp.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.moviesapp.R

@Composable
fun DefaultToolbar(onClickBackButton: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = onClickBackButton) {
            Icon(
                tint = MaterialTheme.colors.primary,
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Volver a la pantalla anterior"
            )
        }
    }
}
