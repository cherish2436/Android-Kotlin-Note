package com.cherish.note.model.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @NonNull
    var label: Int = 0
    @NonNull
    var type: Int = 0
    var imgUrl: String? = null
    var skinType: Int = 0
    @NonNull
    var date: String? = null
    @NonNull
    var content: String? = null
}