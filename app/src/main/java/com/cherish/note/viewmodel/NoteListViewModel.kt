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

class NoteListViewModel constructor(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository
    var noteList: LiveData<List<NoteEntity>>
    var monthList: LiveData<List<String>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val noteDao = AppDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(noteDao)
        noteList = noteRepository.getNoteByLabel(0)
        monthList = noteRepository.getMonthList(0)
//        if (noteList.value == null || noteList.value!!.isEmpty()){
//            val list : ArrayList<NoteEntity> = ArrayList()
//            val note = NoteEntity()
//            note.content = "aaaaaaaaaaaaaaaaaaaaaaab\naaaaaaaaaaaaaaaaaaaaab\naaaaaaaaaaaab"
//            note.date = "2019-01-01 14:00:01"
//            note.label = 0
//            note.type = 0
//            list.add(note)
//            saveData(note)
//
//            val note1 = NoteEntity()
//            note1.content = "bbbbbbbbbbbbbbbbbbbbc\nbbbbbbbbbbbbbbbc\nbbbbbbbbbbbbbbbbbbbbbbbc"
//            note1.date = "2019-02-01 15:00:01"
//            note1.label = 0
//            note1.type = 0
//            list.add(note1)
//            saveData(note1)
//
//            val note2 = NoteEntity()
//            note2.content = "cccccccccccccccccccccccccccccccccccd"
//            note2.date = "2019-02-01 16:00:01"
//            note2.label = 0
//            note2.type = 0
//            list.add(note2)
//            saveData(note2)
//
//            val note3 = NoteEntity()
//            note3.content = "dddddddddddddddddde\nddddddddddddddde"
//            note3.date = "2019-03-01 17:00:01"
//            note3.label = 0
//            note3.type = 0
//            list.add(note3)
//            saveData(note3)
//
//            val note4 = NoteEntity()
//            note4.content = "eeeeeeeeeef\neeeeeeeeeeeeeeeeeeeeeeeeeef"
//            note4.date = "2019-03-01 18:00:01"
//            note4.label = 0
//            note4.type = 0
//            list.add(note4)
//            saveData(note4)
//
//            noteList = noteRepository.getNoteByLabel(0)
//        }
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

    fun getDataFromSearch(word: String, label: Int) = scope.launch {
        Log.e("NoteListViewModel", "getDataFromSearch:I'm working in thread ${Thread.currentThread().name}")
        noteList = noteRepository.getNoteContainWord(word, label)
    }

    fun getMonths(label: Int) = scope.launch(Dispatchers.IO) {
        Log.e("NoteListViewModel", "getMonth:I'm working in thread ${Thread.currentThread().name}")
        monthList = noteRepository.getMonthList(label)
    }
}