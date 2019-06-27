package com.cherish.note.view.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.cherish.note.R
import com.cherish.note.interfaces.SearchContentListener
import com.cherish.note.view.SearchBar
import com.cherish.note.view.WrapContentGridLayoutMananger
import com.cherish.note.view.adapter.NoteListViewAdapter
import com.cherish.note.viewmodel.NoteListViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val floatBtn = findViewById<FloatingActionButton>(R.id.activity_main_btn_floating);
        val appBar = findViewById<AppBarLayout>(R.id.activity_main_layout_bar)
        val greyView = findViewById<View>(R.id.activity_main_view_grey)
        val searchBar = findViewById<SearchBar>(R.id.activity_main_searchBar)
        greyView.setOnClickListener {
            searchBar?.lostFocus()
        }

        searchBar.contentListener(object : SearchContentListener{
            override fun getContent(content: String) {
                if (!TextUtils.isEmpty(content)) {
                    greyView.visibility = View.GONE
                }
            }

            override fun hasFocus(boolean: Boolean) {
                if (boolean) {
                    appBar.visibility = View.GONE
                    greyView.visibility = View.VISIBLE
                    floatBtn.visibility = View.GONE
                } else {
                    appBar.visibility = View.VISIBLE
                    greyView.visibility = View.GONE
                    floatBtn.visibility = View.VISIBLE
                }
            }
        })

        floatBtn.setOnClickListener({
            //TODO:jump to a edit page
        })

        listAdapter = NoteListViewAdapter()
        gridRecyclerView = findViewById(R.id.activity_main_rc_note_card)
        gridRecyclerView!!.layoutManager = WrapContentGridLayoutMananger(this, 2)
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
