package com.krzysztofgluczyk.malapaczkarnia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.krzysztofgluczyk.malapaczkarnia.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val activtyScope = CoroutineScope(Job())

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
        val gson = Gson()

        val httpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val donutService = retrofit.create(DonutService::class.java)

        activtyScope.launch {
            donutService.getDonuts().run {
                map { it.title }.reduce { donut, acc ->
                    "$acc:$donut"
                }
            }.let { wynik ->
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, wynik, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}