package com.ppb.cardview_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppb.cardview_sqlite.connection.DBAdapter
import com.ppb.cardview_sqlite.connection.DBHelper
import com.ppb.cardview_sqlite.model.User

class RVDBActivity : AppCompatActivity() {

    private lateinit var rv_tampilanku: RecyclerView
    lateinit var userDBHelper: DBHelper
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvdbactivity)

        rv_tampilanku = findViewById(R.id.rv_tampilkan)
        rv_tampilanku.setHasFixedSize(true)
        userDBHelper = DBHelper(this)
        list.addAll(userDBHelper.fullData())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = DBAdapter(list)
        rv_tampilanku.adapter = cardData
    }
}