package com.estudo.contacts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudo.contacts.ContactDetail.Companion.EXTRA_CONTACT
import com.estudo.contacts.model.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private val adapter = ContactsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fetchListContact()
        setupRecyclerView()
    }

    private fun fetchListContact() {
        val list = arrayListOf(
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
        getInstanceSharedPreferences().edit() {
            putString("contacts", Gson().toJson(list))
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences {
        return getSharedPreferences("bootcampKotlin.PREFERENCES", Context.MODE_PRIVATE)
    }

    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.closed_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupRecyclerView() {
        val rvMain = findViewById<RecyclerView>(R.id.rv_main)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContact(): List<Contact> {
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val formType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, formType)
    }

    private fun updateList() {
        adapter.updateList(getListContact())
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

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}