package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data

import android.arch.persistence.room.*

/**
 * Created by ThinkSoft on 19/12/2017.
 */
@Dao
interface DaoContact {
    @Query("select * from contacts")
    fun getAllContacts(): List<Contact>

    @Query("select * from contacts where idContact in (:id)")
    fun getContactById(id :Long): Contact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}