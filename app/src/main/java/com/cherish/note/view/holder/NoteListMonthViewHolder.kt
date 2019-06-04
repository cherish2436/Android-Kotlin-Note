package com.cherish.note.view.holder

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.model.db.entity.NoteEntity
import com.cherish.note.utils.UriUtils

class NoteListMonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mContentView = itemView.findViewById<LinearLayout>(R.id.item_note_list_month_layout)

    fun clearContentView() {
        mContentView.removeAllViews()
    }

    fun addView(note: NoteEntity) {
        val view =
            LayoutInflater.from(itemView.context).inflate(R.layout.item_item_note_list_month, mContentView, false)
        val imgView = view.findViewById<ImageView>(R.id.item_item_note_list_month_img)
        imgView.visibility = GONE
        if (!TextUtils.isEmpty(note.imgUrl)) {
            val uri = UriUtils().getImageUri(note.imgUrl!!)
            if (uri != null) {
                imgView.setImageURI(uri)
                imgView.visibility = VISIBLE
            }
        }
        val titleView = view.findViewById<TextView>(R.id.item_item_note_list_month_title)
        val contentView = view.findViewById<TextView>(R.id.item_item_note_list_month_content)
        if (!TextUtils.isEmpty(note.content)) {
            val index = note.content!!.indexOf("\n")
            if (index == -1) {
                titleView.text = note.content
                contentView.visibility = GONE
            } else {
                contentView.visibility = VISIBLE
                titleView.text = note.content!!.substring(0, index)
                contentView.text = note.content!!.substring(index - 1, note.content!!.length)
            }
        } else {
            titleView.text = ""
            contentView.text = ""
        }

        val dateView = view.findViewById<TextView>(R.id.item_item_note_list_month_date)
        if (!TextUtils.isEmpty(note.date)) {
            dateView.text = note.date
        } else {
            dateView.text = ""
        }

        mContentView.addView(view)
    }
}