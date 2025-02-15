package com.ferhatozcelik.codechallenge.ext

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.ferhatozcelik.codechallenge.di.AppContext
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException

fun Uri.save(): Uri? {
    val contentResolver = AppContext.context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "recipe_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Recipes")
    }

    return try {
        val imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri?.let {
            val inputStream = contentResolver.openInputStream(this)
            val outputStream = contentResolver.openOutputStream(it)
            inputStream?.copyTo(outputStream!!)
            inputStream?.close()
            outputStream?.close()
        }
        imageUri
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}