package com.cherish.note.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cherish.note.model.db.dao.NoteDao
import com.cherish.note.model.db.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
open abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = sInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Note_database"
                ).build()
                sInstance = instance
                return instance
            }
        }
    }

    abstract fun noteDao(): NoteDao
}