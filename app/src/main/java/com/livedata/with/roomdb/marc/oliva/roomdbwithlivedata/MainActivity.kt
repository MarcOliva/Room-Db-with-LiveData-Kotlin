package com.livedata.with.roomdb.marc.oliva.roomdbwithlivedata

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            var intent = Intent(applicationContext, ContactDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
