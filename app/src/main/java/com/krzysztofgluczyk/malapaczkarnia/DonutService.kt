package com.krzysztofgluczyk.malapaczkarnia

import retrofit2.Call
import retrofit2.http.GET

interface DonutService {

    @GET("fake-server-donuts/donuts")
    fun getDonuts(): Call<List<Donut>>
}