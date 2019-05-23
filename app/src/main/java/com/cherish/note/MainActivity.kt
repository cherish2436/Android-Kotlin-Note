package com.cherish.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.cherish.note.interfaces.SearchContentListener
import com.cherish.note.view.SearchBar
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private var setUpMenu : MenuItem? = null
    private var modeMenu : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<MaterialToolbar>(R.id.activity_main_toolbar)
        setSupportActionBar(toolBar)
        toolBar.navigationIcon = null

        val contentView = findViewById<LinearLayout>(R.id.activity_main_content_view)
        contentView.requestFocus()
        contentView.isFocusableInTouchMode = true

        val greyView = findViewById<View>(R.id.activity_main_view_grey)
        greyView.setOnClickListener {
            contentView.requestFocus()
            contentView.isFocusableInTouchMode = true
            toolBar.visibility = View.VISIBLE
        }

        val searchBar = findViewById<SearchBar>(R.id.activity_main_searchBar)
        searchBar.contentListener(object : SearchContentListener{
            override fun getContent(content: String) {
                if (!TextUtils.isEmpty(content)) {
                    greyView.visibility = View.GONE
                }
            }

            override fun hasFocus(boolean: Boolean) {
                if (boolean) {
                    toolBar.visibility = View.GONE
                    greyView.visibility = View.VISIBLE
                } else {
                    toolBar.visibility = View.VISIBLE
                    greyView.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_main_menu, menu)
        setUpMenu = menu!!.findItem(R.id.activity_main_menu_setUp)
        modeMenu = menu!!.findItem(R.id.activity_main_menu_mode)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.activity_main_menu_setUp){
            //TODO:to set up page
        } else if(item!!.itemId == R.id.activity_main_menu_mode) {
            //TODO : to list mode
        }
        return super.onOptionsItemSelected(item)
    }
}
