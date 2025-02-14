package com.ferhatozcelik.codechallenge.ext

import android.content.Context
import android.net.Uri
import com.ferhatozcelik.codechallenge.di.AppContext
import java.io.File
import java.io.IOException

fun Uri.saveToStorage(): String? {
    return try {
        val contentResolver = AppContext.context.contentResolver
        val inputStream = contentResolver.openInputStream(this)

        val fileName = "recipe_image_${System.currentTimeMillis()}.jpg"
        val file = File(AppContext.context.filesDir, fileName)

        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        file.absolutePath
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
