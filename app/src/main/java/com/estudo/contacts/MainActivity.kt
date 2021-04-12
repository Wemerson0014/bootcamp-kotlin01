package com.estudo.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
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
            arrayListOf(
                Contact(
                    "Wemerson Fernandes",
                    "(31) 99999-9999",
                    "img.png"
                ), Contact(
                    "Debora Cristina",
                    "(31) 99999-9999",
                    "img.png"
                )
            )
        )
    }

    private fun showToast(messege: String) {
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId) {
           R.id.item_menu_1 -> {
               showToast(getString(R.string.toast_1))
               true
           }
           R.id.item_menu_2 -> {
               showToast(getString(R.string.toast_2))
               true
           }
           R.id.item_menu_3 -> {
               showToast(getString(R.string.toast_3))
               true
           }
           else -> super.onOptionsItemSelected(item)
       }
    }
}