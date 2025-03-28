package com.example.marsphotos.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.marsphotos.data.AdministradorDao
import com.example.marsphotos.data.GrupoDao
import com.example.marsphotos.data.NotificacionDao
import com.example.marsphotos.data.SeccionDao
import com.example.marsphotos.data.UsuarioDao
import com.example.marsphotos.model.Administrador
import com.example.marsphotos.model.Grupo
import com.example.marsphotos.model.Notificacion
import com.example.marsphotos.model.Seccion
import com.example.marsphotos.model.Usuario
import java.util.Date


@Database(entities = [Usuario::class, Seccion::class, Notificacion::class, Administrador::class, Grupo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun seccionDao(): SeccionDao
    abstract fun notificacionDao(): NotificacionDao
    abstract fun administradorDao(): AdministradorDao
    abstract fun grupoDao(): GrupoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mi_base_datos"
                )
                    .addCallback(DatabaseCallback(context)) // Agregar el callback
                    .build().also { INSTANCE = it }
            }
        }

        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Crear datos iniciales aquí
                Thread {
                    val dbInstance = getDatabase(context)
                    dbInstance.usuarioDao().insertUsuario(
                        Usuario(
                            usuario = "admin",
                            password = "1234",
                            nombre = "Administrador",
                            correo = "admin@mail.com",
                            edad = 30,
                            id_grupo = 1
                        )
                    )
                    dbInstance.seccionDao().insertSeccion(
                        Seccion(
                            categoria = "General",
                            nombre = "Noticias",
                            descripcion = "Sección de noticias importantes"
                        )
                    )
                    dbInstance.administradorDao().insertAdministrador(
                        Administrador(
                            nombre = "Admin",
                            usuario = "adminUser",
                            password = "securePass",
                            correo = "admin@example.com"
                        )
                    )
                }.start()
            }
        }
    }
}
