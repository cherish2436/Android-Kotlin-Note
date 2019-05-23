package com.cherish.note

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.cherish.note.interfaces.SearchContentListener
import com.cherish.note.view.SearchBar
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private var setUpMenu : MenuItem? = null
    private var modeMenu : MenuItem? = null

    private var isExpand: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar = findViewById<MaterialToolbar>(R.id.activity_main_toolbar)
        setSupportActionBar(toolBar)
        toolBar.navigationIcon = null

        val searchBar = findViewById<SearchBar>(R.id.activity_main_searchBar)
        val greyView = findViewById<View>(R.id.activity_main_view_grey)
        greyView.setOnClickListener {
            searchBar.clear()
        }

        val windowUtils = WindowUtils()
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
                    findViewById<View>(R.id.activity_main_view_line).visibility = View.VISIBLE
                } else {
                    toolBar.visibility = View.VISIBLE
                    greyView.visibility = View.GONE
                    windowUtils.closeSoftInputWindow(this@MainActivity)
                    findViewById<View>(R.id.activity_main_view_line).visibility = View.GONE
                }
            }
        })


        findViewById<LinearLayout>(R.id.activity_main_title_second).setOnClickListener {
            titleClick()
        }
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

    private fun imgAnimator(float: Float) {
        val titleImg = findViewById<ImageView>(R.id.activity_main_title_img)
        val objectAnimator = ObjectAnimator.ofFloat(titleImg, "rotation", float)
        objectAnimator.duration = 500
        objectAnimator.start()
    }

    private fun titleClick() {
        if (isExpand) {
            imgAnimator(0f)
        } else {
            imgAnimator(180f)
        }

        isExpand = !isExpand
    }
}
