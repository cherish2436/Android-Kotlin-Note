package com.cherish.note.view

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

open class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mContext : Context = context
    private var mListener : SearchContentListener? = null
    private var layout: RelativeLayout? = null
    private var hasFocus: Boolean = false
    private var editContent: EditText? = null

    init {
        layout = LayoutInflater.from(mContext).inflate(R.layout.view_search, this, false) as RelativeLayout

        val imageLeft = layout!!.findViewById(R.id.view_search_img_left) as ImageView
        val imageDelete = layout!!.findViewById(R.id.view_search_img_delete) as ImageView
        editContent = layout!!.findViewById(R.id.view_search_tv_content) as EditText

        editContent!!.clearFocus()
        editContent!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("SearchBar", "before")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.isNotEmpty()) {
                    if (mListener != null) mListener!!.getContent(p0.toString())
                    if (!imageDelete.isVisible) {
                        imageDelete.visibility = View.VISIBLE
                    }
                } else {
                    if (imageDelete.isVisible) {
                        imageDelete.visibility = GONE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("SearchBar", "after")
            }
        })

        editContent!!.onFocusChangeListener = OnFocusChangeListener { _, p1 ->
            if (p1) {
                imageLeft.setImageResource(R.drawable.ic_arrow_left)
                editContent!!.hint = ""
                hasFocus = true
                mListener!!.hasFocus(true)
            } else {
                imageLeft.setImageResource(R.drawable.ic_search)
                editContent!!.hint = "搜索标签"
                hasFocus = false
                mListener!!.hasFocus(false)
            }
            imageLeft.setOnClickListener {
                imageLeft.setImageResource(R.drawable.ic_search)
                if (hasFocus) clear()
            }
        }

        imageDelete.setOnClickListener { editContent!!.setText("") }

        addView(layout)
    }

    fun clear() {
        layout!!.requestFocus()
        layout!!.isFocusableInTouchMode = true
        editContent!!.clearFocus()
    }

    fun contentListener(listener: SearchContentListener){
        mListener = listener
    }
}