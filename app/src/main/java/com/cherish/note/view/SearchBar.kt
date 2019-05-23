package com.cherish.note.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

    init {
        val layout : RelativeLayout = LayoutInflater.from(mContext).inflate(R.layout.view_search, this, false) as RelativeLayout

        val image_left = layout.findViewById(R.id.view_search_img_left) as ImageView
        val image_delete = layout.findViewById(R.id.view_search_img_delete) as ImageView
        val editContent = layout.findViewById(R.id.view_search_tv_content) as EditText

        editContent.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("SearchBar", "before")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.isNotEmpty()) {
                    if (mListener != null) mListener!!.getContent(p0.toString())
                    if(!image_delete.isVisible) {
                        image_delete.visibility = View.VISIBLE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("SearchBar", "after")
            }
        })

        editContent.onFocusChangeListener = OnFocusChangeListener { _, p1 ->
            if (p1) {
                image_left.setImageResource(R.drawable.ic_arrow_left)
                editContent.hint = ""
            } else {
                image_left.setImageResource(R.drawable.ic_search)
                editContent.hint = "搜索标签"
            }
            image_left.setOnClickListener {
                image_left.setImageResource(R.drawable.ic_search)
                mListener!!.hasFocus(false)
            }
        }

        addView(layout)
    }

    fun contentListener(listener: SearchContentListener){
        mListener = listener
    }
}