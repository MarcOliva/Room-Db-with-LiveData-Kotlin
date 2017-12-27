package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ThinkSoft on 19/12/2017.
 */
@Entity(tableName = "contacts")
data class Contact(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idContact")
        var id: Int = 0,

        @ColumnInfo(name = "name")
        var name: String = "",

        @ColumnInfo(name = "number")
        var number: String = ""

)