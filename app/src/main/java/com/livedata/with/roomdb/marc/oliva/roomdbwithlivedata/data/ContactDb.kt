package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by ThinkSoft on 19/12/2017.
 */
@Database(entities =[Contact::class] ,version = 1,exportSchema = false)
abstract class ContactDb : RoomDatabase() {
    private var INSTANCE: ContactDb? = null
    fun getDataBase(context: Context) : ContactDb {
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.applicationContext, ContactDb::class.java,"contacts-db")
                    .allowMainThreadQueries().build()

        }
        return INSTANCE as ContactDb
    }
    abstract fun daoContact(): DaoContact
}