package com.cherish.note.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cherish.note.model.db.dao.NoteDao
import com.cherish.note.model.db.entity.NoteEntity

/**
 * 统一数据源
 */
class NoteRepository constructor(private val noteDao: NoteDao) {
    fun getNoteByLabel(label: Int): LiveData<List<NoteEntity>> {
        return noteDao.getNoteListByLabel(label)
    }

    fun getNoteContainWord(word: String, label: Int): LiveData<List<NoteEntity>> {
        return noteDao.getNoteListContainContent(word, label)
    }

    fun getMonthList(label: Int): LiveData<List<String>> {
        return noteDao.getNoteListDate(label)
    }

    @WorkerThread
    fun saveData(note: NoteEntity) {
        noteDao.saveNoteData(note)
    }

    @WorkerThread
    fun updateData(note: NoteEntity) {
        noteDao.updateNoteData(note)
    }

    @WorkerThread
    fun deleteData(noteList: List<NoteEntity>) {
        if (noteList.isNotEmpty()) {
            for (note in noteList) {
                noteDao.deleteNoteData(note)
            }
        }
    }
}