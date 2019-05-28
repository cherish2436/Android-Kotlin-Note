package com.cherish.note.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0
    @NonNull
    private var label: String? = null
    @NonNull
    private var type: String? = null
    private var imgUrl: String? = null
    private var skinType: Int = 0
    @NonNull
    private var title: String? = null
    @NonNull
    private var time: String? = null
    @NonNull
    private var content: String? = null
}