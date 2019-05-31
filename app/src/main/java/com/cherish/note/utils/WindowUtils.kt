package com.cherish.note.utils

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


open class WindowUtils {

    /**
     * 关闭软键盘
     */
    fun closeSoftInputWindow(context: Activity) {
        val view = context.window.peekDecorView()
        if (view != null) {
            val inputManger = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
            inputManger!!.hideSoftInputFromWindow(view!!.windowToken, 0)
        }
    }

}