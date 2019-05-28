package com.cherish.note.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import com.cherish.note.R

class PopWindowUtils {

    fun getListPopWindow(parent: View): PopupWindow {
        val layout: View = LayoutInflater.from(parent.context).inflate(R.layout.pop_type_list, null, false)


        val window: PopupWindow = PopupWindow(layout)
        window.showAsDropDown(parent)
        return window
    }
}