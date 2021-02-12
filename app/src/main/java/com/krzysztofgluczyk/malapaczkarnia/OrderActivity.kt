package com.krzysztofgluczyk.malapaczkarnia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.krzysztofgluczyk.malapaczkarnia.databinding.ActivityOrderBinding
import kotlin.random.Random

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
            .add(R.id.fragment_container, InfoFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.navigate -> {
                findNavController(R.id.navHost).run {
                    when (currentDestination?.id) {
                        R.id.infoFragment -> if (Random.nextBoolean()) R.id.infoFragment2 else R.id.infoFragment3
                        R.id.infoFragment2, R.id.infoFragment3 -> R.id.infoFragment
                        else -> throw IllegalAccessException()
                    }.let {
                        navigate(it)
                    }
                }
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