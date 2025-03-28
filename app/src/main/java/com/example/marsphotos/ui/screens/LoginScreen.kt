package com.example.marsphotos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marsphotos.model.Usuario



// ui/LoginScreen.kt
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(15.dp),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "Notify",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(1.dp)
            )
        }
        Text(
            text = "Inicio de sesión",
            style = MaterialTheme.typography.titleLarge,

            modifier = Modifier
                .padding(10.dp)
        )
       Box(
            modifier = Modifier

                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center

        ){
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp)
                    .padding(1.dp, 1.dp, 1.dp, 1.dp)
                    .border(
                        3.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(0.dp, 25.dp, 25.dp, 25.dp)
                    )

            ){

                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                    )

                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Usuario") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        if (username == "admin" && password == "1234") onLoginSuccess()
                    }) {
                        Text("Iniciar sesión")
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Crear Cuenta",
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline,

                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Lol(){
    LoginScreen {   }
}

@Composable
fun AdminScreen() {
    var usuarios by remember {
        mutableStateOf(
            mutableListOf(
                Usuario(1, "admin", "1234", "Administrador", "admin@mail.com", 30, 1),
                Usuario(2, "user1", "pass1", "Usuario Uno", "user1@mail.com", 25, 2)
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Administración de Usuarios", style = MaterialTheme.typography.labelMedium, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxHeight().padding(bottom = 16.dp)) {
            items(usuarios) { usuario ->
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nombre: ${usuario.nombre}")
                        Text("Usuario: ${usuario.usuario}")
                        Text("Correo: ${usuario.correo}")
                        Text("Edad: ${usuario.edad}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { usuarios.remove(usuario) }) {
                            Text("Eliminar Usuario")
                        }
                    }
                }
            }
        }

        Button(onClick = {
            // Lógica para agregar usuario (Ejemplo básico)
            usuarios.add(
                Usuario(
                    id = usuarios.size + 1,
                    usuario = "newuser",
                    password = "newpass",
                    nombre = "Nuevo Usuario",
                    correo = "newuser@mail.com",
                    edad = 20,
                    id_grupo = 3
                )
            )
        }) {
            Text("Agregar Usuario")
        }
    }
}


