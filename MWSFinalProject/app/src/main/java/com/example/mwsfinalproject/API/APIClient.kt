package com.example.restapiui.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    const val BASE_URL = "http://192.168.100.6/MWSFinal/public/"

    fun create(): APIInterface { /* membuat interface/koneksi/command ke APIInterface */

        /* Menghubungkan ke URL dan membuat converter Json/Gson untuk data yang didapat */
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIInterface::class.java)
    }
}