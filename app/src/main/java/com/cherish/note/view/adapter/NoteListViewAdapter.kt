package com.cherish.note.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.model.db.entity.NoteEntity
import com.cherish.note.view.holder.NoteListViewHolder

class NoteListViewAdapter internal constructor() : RecyclerView.Adapter<NoteListViewHolder>() {
    private var noteList = emptyList<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note_list, parent, false)

        return NoteListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.initCard(noteList.get(position))
    }

    fun setData(noteList: List<NoteEntity>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }
}