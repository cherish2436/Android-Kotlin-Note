package com.cherish.note.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.interfaces.SearchContentListener
import com.cherish.note.view.SearchBar
import com.cherish.note.view.adapter.NoteListViewAdapter
import com.cherish.note.viewmodel.NoteListViewModel
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private var setUpMenu : MenuItem? = null
    private var modeMenu : MenuItem? = null
    private lateinit var gridRecyclerView: RecyclerView
    private lateinit var listAdapter: NoteListViewAdapter
    private lateinit var noteListViewModel: NoteListViewModel

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

        listAdapter = NoteListViewAdapter()
        gridRecyclerView = findViewById(R.id.activity_main_rc_note_card)
        gridRecyclerView!!.layoutManager = GridLayoutManager(this, 2)
        gridRecyclerView!!.adapter = listAdapter

        noteListViewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        noteListViewModel.noteList.observe(
            this,
            Observer { noteList -> noteList?.let { listAdapter.setData(noteList) } })
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
