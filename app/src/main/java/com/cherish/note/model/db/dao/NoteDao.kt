package com.cherish.note.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cherish.note.model.db.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note WHERE label = :label")
    fun getNoteListByLabel(label: Int): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE content LIKE :content AND label = :label")
    fun getNoteListContainContent(content: String, label: Int): LiveData<List<NoteEntity>>

    @Query("SELECT strftime('%Y-%m', date) FROM note WHERE label = :label GROUP BY strftime('%Y-%m', date)")
    fun getNoteListDate(label: Int): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNoteData(note: NoteEntity)

    @Update
    fun updateNoteData(note: NoteEntity)

    @Delete
    fun deleteNoteData(note: NoteEntity)
}