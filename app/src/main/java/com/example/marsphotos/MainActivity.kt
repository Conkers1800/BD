/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.marsphotos.model.Usuario
import com.example.marsphotos.network.AppDatabase
import com.example.marsphotos.ui.screens.AdminScreen
import com.example.marsphotos.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa la base de datos explícitamente al abrir la app
        val database = AppDatabase.getDatabase(this)
        Thread {
            val dao = database.usuarioDao()
            dao.getAllUsuariosCursor(
            )
        }.start()
        setContent {
            val isLoggedIn = remember { mutableStateOf(false) }

            if (isLoggedIn.value) {
                AdminScreen()
            } else {
                LoginScreen(onLoginSuccess = {
                    isLoggedIn.value = true
                })
            }
        }
    }
}
