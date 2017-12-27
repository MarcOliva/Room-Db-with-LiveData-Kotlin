package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.Contact
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.ContactDb
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.DaoContact
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetailsActivity : AppCompatActivity() {


    private var daoContact: DaoContact? = null
    private var viewModel: ContactListViewModel? = null

    private var currentContact: Int? = null
    private var contact: Contact? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        var db: ContactDb = ContactDb.getDataBase(this)

        daoContact = db.daoContact()

        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        currentContact = intent.getIntExtra("idContact", -1)
        if (currentContact != -1) {
            setTitle(R.string.edit_contact_title)
            contact = daoContact!!.getContactById(currentContact!!)
            name_edit_text.setText(contact!!.name)
            number_edit_text.setText(contact!!.number)
        } else {
            setTitle(R.string.add_contact_title)
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.done_item -> {
                if (currentContact == -1) {
                    saveContact()
                    Toast.makeText(this, getString(R.string.save_contact), Toast.LENGTH_SHORT).show()
                } else {
                    updateContact()
                    Toast.makeText(this, getString(R.string.update_contact), Toast.LENGTH_SHORT).show()
                }

                finish()
            }
            R.id.delete_item -> {
                deleteContact()
                Toast.makeText(this, getString(R.string.delete_contact), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (currentContact == -1) {
            menu.findItem(R.id.delete_item).isVisible = false
        }
        return true
    }

    private fun saveContact() {
        var nameContact = name_edit_text.text.toString()
        var numberContact = number_edit_text.text.toString()
        var contact = Contact(0, nameContact, numberContact)
        viewModel!!.addContact(contact)
    }

    private fun deleteContact() {
        daoContact!!.deleteContact(contact!!)
    }

    private fun updateContact() {
        var nameContact = name_edit_text.text.toString()
        var numberContact = number_edit_text.text.toString()
        var contact = Contact(contact!!.id, nameContact, numberContact)
        daoContact!!.updateContact(contact)
    }
}
