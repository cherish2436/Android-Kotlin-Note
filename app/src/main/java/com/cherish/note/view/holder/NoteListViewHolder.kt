package com.cherish.note.view.holder

import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.model.db.entity.NoteEntity
import com.cherish.note.utils.UriUtils


class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mImageView: ImageView = itemView.findViewById(R.id.item_note_list_card_img)
    private val mTitleView: TextView = itemView.findViewById(R.id.item_note_list_card_title)
    private val mContentView: TextView = itemView.findViewById(R.id.item_note_list_card_content)
    private val mDateView: TextView = itemView.findViewById(R.id.item_note_list_card_date)

    init {
        val layoutParams = itemView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        itemView.layoutParams = layoutParams
    }

    fun initCard(note: NoteEntity) {
        if (note == null || TextUtils.isEmpty(note.content)) return
        if (note.imgUrl != null) {
            setImg(note.imgUrl!!)
        }
        setContent(note.content!!)
        setDate(note.date!!)
    }

    fun setDate(date: String) {
        mDateView.text = date
    }

    fun setContent(content: String) {
        val index = content!!.indexOf("\n")
        if (index != -1) {
            val tile = content.substring(0, index)
            mTitleView.text = tile
            mContentView.text = content.substring(index - 1, content.length - 1)
            mContentView.visibility = VISIBLE
        } else {
            mTitleView.text = content
            mContentView.visibility = GONE
        }
    }

    fun setImg(path: String) {
        if (!TextUtils.isEmpty(path)) {
            val uri = UriUtils().getImageUri(path)
            if (uri != null) {
                mImageView.setImageURI(uri)
            }
        }
    }
}