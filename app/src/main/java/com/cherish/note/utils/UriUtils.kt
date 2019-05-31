package com.cherish.note.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

open class UriUtils {

    fun getRealFilePath(context: Context, uri: Uri): String {
        if (uri == null) return ""
        val schema = uri.scheme
        var data = ""
        if (schema == null) {
            data = uri.path
        } else if (ContentResolver.SCHEME_FILE == schema) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT == schema) {
            val cusor =
                context.contentResolver.query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            if (cusor != null) {
                if (cusor.moveToFirst()) {
                    val index = cusor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cusor.getString(index)
                    }
                }
                cusor.close()
            }
        }
        return data
    }

    fun getImageUri(path: String): Uri? {
        val file = File(path)
        if (file.exists()) {
            return Uri.fromFile(file)
        }

        return null
    }
}