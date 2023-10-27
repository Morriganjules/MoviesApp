package com.example.moviesapp.movieLoginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviesapp.R

@Composable
fun MovieLoginScreen() {
    Login()
}

@Composable
fun Login(

) {
    val user = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            modifier = Modifier.size(300.dp),
            tint = MaterialTheme.colors.primary,
            painter = painterResource(id = R.drawable.ic_movie_filter),
            contentDescription = "Volver a la pantalla anterior"
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                inputText = user.value,
                label = "Usuario",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "",
                        tint = Color.DarkGray
                    )
                },
                onTextChange = {
                    user.value = it
                }
            )
            CustomTextField(
                inputText = password.value,
                label = "Contraseña",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "",
                        tint = Color.DarkGray
                    )
                },
                onTextChange = {
                    password.value = it
                }
            )
            CustomTextField(
                inputText = email.value,
                label = "Email",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "",
                        tint = Color.DarkGray
                    )
                },
                onTextChange = {
                    email.value = it
                }
            )
        }
    }

}

@Composable
fun CustomTextField(
    label: String,
    icon: @Composable (() -> Unit)? = null,
    inputText: String,
    onTextChange: (String) -> Unit,
) {

    var value by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        value = inputText,
        onValueChange = onTextChange ,
        label = {
            Text(text = label)
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.Black
        ),
        trailingIcon = icon
    )
}