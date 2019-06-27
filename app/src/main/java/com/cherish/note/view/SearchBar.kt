package com.cherish.note.view

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.cherish.note.R
import com.cherish.note.interfaces.SearchContentListener
import com.cherish.note.utils.WindowUtils

open class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mContext : Context = context
    private var mListener : SearchContentListener? = null

    init {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.view_search, this, false) as RelativeLayout
        requestFocus()
        isFocusableInTouchMode = true

        val imageLeft = layout.findViewById(R.id.view_search_img_left) as ImageView
        val imageDelete = layout.findViewById(R.id.view_search_img_delete) as ImageView
        val editContent = layout.findViewById(R.id.view_search_tv_content) as EditText

        editContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("SearchBar", "before")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null && p0!!.isNotEmpty()) {
                    if (mListener != null) mListener!!.getContent(p0.toString())
                    if (!imageDelete.isVisible) {
                        imageDelete.visibility = View.VISIBLE
                    }
                } else {
                    if (imageDelete.isVisible) {
                        imageDelete.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("SearchBar", "after")
            }
        })

        editContent.onFocusChangeListener = OnFocusChangeListener { _, p1 ->
            if (p1) {
                imageLeft.setImageResource(R.drawable.ic_arrow_left)
                editContent.hint = ""
                if (mListener != null) {
                    mListener!!.hasFocus(true)
                }
                imageLeft.setOnClickListener {
                    requestFocus()
                    isFocusableInTouchMode = true
                }
            } else {
                imageLeft.setImageResource(R.drawable.ic_search)
                editContent.hint = "搜索标签"
                imageLeft.setOnClickListener { }
                if (mListener != null) {
                    mListener!!.hasFocus(false)
                }
                editContent.text = null
                WindowUtils().closeSoftInputWindow(mContext as Activity)
            }
        }

        addView(layout)
    }

    fun contentListener(listener: SearchContentListener){
        mListener = listener
    }

    fun lostFocus() {
        requestFocus()
        isFocusableInTouchMode = true
    }
}