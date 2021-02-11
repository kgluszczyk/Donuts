package com.krzysztofgluczyk.malapaczkarnia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.krzysztofgluczyk.malapaczkarnia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            setContentView(root)
            paczek.setOnClickListener {
                zamowienie.text.toString().let { zamowienie ->
                    Intent(this@MainActivity, OrderActivity::class.java).apply {
                        putExtra(OrderActivity.ZAMOWIENIE_KEY, zamowienie)
                    }.run {
                        this@MainActivity.startActivity(this)
                    }
                }
            }
        }
    }
}