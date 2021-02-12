package com.krzysztofgluczyk.malapaczkarnia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.krzysztofgluczyk.malapaczkarnia.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    companion object {

        const val ZAMOWIENIE_KEY = "ZAMOWIENIE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityOrderBinding.inflate(layoutInflater).run {
            setContentView(root)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            intent.getStringExtra(ZAMOWIENIE_KEY)?.also {
                zamowienie.text = it
            }
        }
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .add(android.R.id.content, InfoFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Pop", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.navigate -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_navigation, menu)
        return true
    }
}