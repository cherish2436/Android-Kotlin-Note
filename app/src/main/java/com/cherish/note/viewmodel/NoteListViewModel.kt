package com.cherish.note.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cherish.note.model.db.AppDatabase
import com.cherish.note.model.db.entity.NoteEntity
import com.cherish.note.model.repository.NoteRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class NoteListViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    var noteList: LiveData<List<NoteEntity>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val noteDao = AppDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(noteDao)
        noteList = noteRepository.getNoteByLabel(0)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
        scope.cancel()
    }

    fun saveData(note: NoteEntity) = scope.launch(Dispatchers.IO) {
        Log.e("NoteListViewModel", "insert:I'm working in thread ${Thread.currentThread().name}")
        noteRepository.saveData(note)
    }

    fun deleteData(noteList: List<NoteEntity>) = scope.launch(Dispatchers.IO) {
        Log.e("NoteListViewModel", "delete:I'm working in thread ${Thread.currentThread().name}")
        noteRepository.deleteData(noteList)
    }

    fun updateData(note: NoteEntity) = scope.launch(Dispatchers.IO) {
        Log.e("NoteListViewModel", "update:I'm working in thread ${Thread.currentThread().name}")
        noteRepository.updateData(note)
    }

    fun getDataFromLabel(label: Int) = scope.launch(Dispatchers.IO) {
        Log.e("NoteListViewModel", "query:I'm working in thread ${Thread.currentThread().name}")
        noteList = noteRepository.getNoteByLabel(label)
    }
}