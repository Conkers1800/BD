package com.example.marsphotos.model

import androidx.room.Entity
import androidx.room.PrimaryKey



// database/Usuario.kt
@Entity(tableName = "Usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuario: String,
    val password: String,
    val nombre: String,
    val correo: String,
    val edad: Int,
    val id_grupo: Int
)

@Entity(tableName = "Seccion")
data class Seccion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoria: String,
    val nombre: String,
    val descripcion: String,
)

@Entity(tableName = "Notificacion")
data class Notificacion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val remitenteId: Int,
    val receptorId: Int,
    val descripcion: String,

)

@Entity(tableName = "Administrador")
data class Administrador(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val usuario: String,
    val password: String,
    val correo: String
)

@Entity(tableName = "Grupo")
data class Grupo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val numUsuarios: Int,
    val idAdmin: Int,
    val idUsuario: Int,
    val nombre: String,
    val descripcion: String
)