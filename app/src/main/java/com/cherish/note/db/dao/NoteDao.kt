package com.cherish.note.db.dao

import androidx.room.*
import com.cherish.note.db.entity.NoteEntity

@Dao
open interface NoteDao {
    @Query("SELECT * FROM note WHERE label = :lable")
    fun getNoteListByLable(lable: String): List<NoteEntity>

    @Query("SELECT * FROM note WHERE title LIKE :title AND label = :label")
    fun getNoteListContainTitle(title: String, label: String): List<NoteEntity>

    @Query("SELECT * FROM note WHERE content LIKE :content AND label = :label")
    fun getNoteListContainContent(content: String, label: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNoteData(note: NoteEntity)

    @Update
    fun updateNoteData(note: NoteEntity)

    @Delete
    fun deleteNoteData(note: NoteEntity)
}