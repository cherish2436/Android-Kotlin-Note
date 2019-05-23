package com.cherish.note.interfaces

open interface SearchContentListener {
    fun getContent(content : String)
    fun hasFocus(boolean: Boolean)
}