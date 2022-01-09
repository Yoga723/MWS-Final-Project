package com.example.restapiui.API

import com.example.mwsfinalproject.Package_Burger.BurgerModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    /* Berisi fungsi-fungsi seperti mengambil data dari Students, Mencari data berdasarkan ID, menambahkan data
        ,mengedit data, dan menghapus data Students*/

    @GET("listburger")
    fun getBurgers(): Call<ArrayList<BurgerModel>>

    @GET("listburger/{ID}")
    fun getBurger(
        @Path("ID") ID: Int
    ): Call<ArrayList<BurgerModel>>

    @FormUrlEncoded
    @POST("listburger")
    fun createBurgers(
        @Field("Nama_Snack") NIM: String,
        @Field("Harga") Nama: String,
        @Field("Deskripsi") JK: String,
    ): Call<BurgerModel>

    @FormUrlEncoded
    @PUT("listburger/{ID}")
    fun updateBurger(
        @Path("ID") ID: Int?,
        @Field("Nama_Snack") NIM: String,
        @Field("Harga") Nama: String,
        @Field("Deskripsi") JK: String,
    ): Call<BurgerModel>

    @DELETE("listburger/{ID}")
    fun deleteBurger(
        @Path("ID") ID: Int
    ): Call<Unit>
}
