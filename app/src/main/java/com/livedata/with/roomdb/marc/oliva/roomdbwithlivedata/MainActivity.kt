package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.Contact
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.ContactDb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactRecyclerAdapter.OnItemClickListener {


    private var contactRecyclerView: RecyclerView? = null
    private var recyclerViewAdapter: ContactRecyclerAdapter? = null

    private var viewModel: ContactListViewModel? = null

    private var db: ContactDb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = ContactDb.getDataBase(this)

        contactRecyclerView = findViewById(R.id.recycler_view)
        recyclerViewAdapter = ContactRecyclerAdapter(arrayListOf(), this)

        contactRecyclerView!!.layoutManager = LinearLayoutManager(this)
        contactRecyclerView!!.adapter = recyclerViewAdapter

        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)

        viewModel!!.getListContacts().observe(this, Observer { contacts ->
            recyclerViewAdapter!!.addContacts(contacts!!)
        })
        fab.setOnClickListener {
            var intent = Intent(applicationContext, ContactDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_items -> {
                deleteAllContacts()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllContacts() {
        db!!.daoContact().deleteAllContacts()
    }

    override fun onItemClick(contact: Contact) {
        var intent = Intent(applicationContext, ContactDetailsActivity::class.java)
        intent.putExtra("idContact", contact.id)
        startActivity(intent)
    }
}

