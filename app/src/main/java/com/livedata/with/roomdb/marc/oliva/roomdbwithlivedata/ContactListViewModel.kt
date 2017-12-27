package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.Contact
import com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data.ContactDb

/**
 * Created by ThinkSoft on 21/12/2017.
 */
class ContactListViewModel(application: Application) : AndroidViewModel(application) {

    var listContact: LiveData<List<Contact>>
    private val appDb: ContactDb

    init {
        appDb = ContactDb.getDataBase(this.getApplication())
        listContact = appDb.daoContact().getAllContacts()
    }

    fun getListContacts(): LiveData<List<Contact>> {
        return listContact
    }

    fun addContact(contact: Contact) {
        addAsynTask(appDb).execute(contact)
    }


    class addAsynTask(db: ContactDb) : AsyncTask<Contact, Void, Void>() {
        private var contactDb = db
        override fun doInBackground(vararg params: Contact): Void? {
            contactDb.daoContact().insertContact(params[0])
            return null
        }

    }

}