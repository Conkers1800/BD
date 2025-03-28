package com.example.marsphotos.data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.marsphotos.model.Administrador
import com.example.marsphotos.model.Grupo
import com.example.marsphotos.model.Notificacion
import com.example.marsphotos.model.Seccion
import com.example.marsphotos.model.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    fun getAllUsuariosCursor(): Cursor

    @Insert
    fun insertUsuario(usuario: Usuario): Long

    @Update
    fun updateUsuario(usuario: Usuario)

    @Delete
    fun deleteUsuario(usuario: Usuario)
}

@Dao
interface SeccionDao {
    @Query("SELECT * FROM Seccion")
    fun getAllSecciones(): List<Seccion>

    @Insert
    fun insertSeccion(seccion: Seccion): Long
}

@Dao
interface NotificacionDao {
    @Query("SELECT * FROM Notificacion WHERE receptorId = :userId")
    fun getNotificacionesByUser(userId: Int): List<Notificacion>

    @Insert
    fun insertNotificacion(notificacion: Notificacion): Long
}

@Dao
interface AdministradorDao {
    @Query("SELECT * FROM Administrador")
    fun getAllAdministradores(): List<Administrador>

    @Insert
    fun insertAdministrador(admin: Administrador): Long
}

@Dao
interface GrupoDao {
    @Query("SELECT * FROM Grupo")
    fun getAllGrupos(): List<Grupo>

    @Insert
    fun insertGrupo(grupo: Grupo): Long
}

