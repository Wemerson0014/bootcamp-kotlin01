package com.estudo.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.contacts.model.Contact

class MainActivity : AppCompatActivity() {

    private val adapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        updateList()
    }

    private fun setupRecyclerView() {
        val rvMain = findViewById<RecyclerView>(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun updateList() {
        adapter.updateList(
            arrayListOf(Contact(
                "Wemerson Fernandes",
                "(31) 99999-9999",
                "img.png"
            ), Contact(
                "Debora Cristina",
                "(31) 99999-9999",
                "img.png"
            ))
        )
    }
}