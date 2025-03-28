package com.example.marsphotos.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.marsphotos.model.Usuario
import com.example.marsphotos.network.AppDatabase

class UsuarioProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "com.example.marsphotos.provider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/Usuario")
    }

    private lateinit var database: AppDatabase

    override fun onCreate(): Boolean {
        database = AppDatabase.getDatabase(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return database.usuarioDao().getAllUsuariosCursor()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = database.usuarioDao().insertUsuario(
            Usuario(
                usuario = values?.getAsString("usuario") ?: "",
                password = values?.getAsString("password") ?: "",
                nombre = values?.getAsString("nombre") ?: "",
                correo = values?.getAsString("correo") ?: "",
                edad = values?.getAsInteger("edad") ?: 0,
                id_grupo = values?.getAsInteger("id_grupo") ?: 0
            )
        )
        context?.contentResolver?.notifyChange(uri, null)
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        // Implementar actualización
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implementar eliminación
        return 0
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/$AUTHORITY.Usuario"
    }
}
