package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class ContactDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        var currentContact = intent.getIntExtra("idContact", -1)
        if (currentContact != -1) {
            setTitle(R.string.edit_contact_title)
        } else {
            setTitle(R.string.add_contact_title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.done_item -> Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
            R.id.delete_item -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
