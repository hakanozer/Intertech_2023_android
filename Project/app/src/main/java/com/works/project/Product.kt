package com.works.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class Product : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itm_logout) {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("jwt")
            editor.commit()

            val intent = Intent(this@Product, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true
    }

}