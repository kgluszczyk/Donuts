package com.krzysztofgluczyk.malapaczkarnia

import android.os.Bundle
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
            intent.getStringExtra(ZAMOWIENIE_KEY)?.also {
                zamowienie.text = it
            }
        }
    }
}