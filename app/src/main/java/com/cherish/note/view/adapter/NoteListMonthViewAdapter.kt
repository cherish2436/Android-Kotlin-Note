package com.cherish.note.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.model.db.entity.NoteEntity
import com.cherish.note.view.holder.NoteListMonthViewHolder

class NoteListMonthViewAdapter : RecyclerView.Adapter<NoteListMonthViewHolder>() {
    private var noteList = emptyList<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListMonthViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note_list_month, parent, false)

        return NoteListMonthViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListMonthViewHolder, position: Int) {
        holder.clearContentView()
    }
}