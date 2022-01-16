package com.example.restapiui.API

import com.example.mwsfinalproject.Package_Burger.BurgerModel
import com.example.mwsfinalproject.Package_Pizza.PizzaModel
import com.example.restapiui.SnackModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    /* Berisi fungsi-fungsi seperti mengambil data dari Students, Mencari data berdasarkan ID, menambahkan data
        ,mengedit data, dan menghapus data Students*/


    /* Fungsi untuk tabel Burger */
    @GET("listburger")
    fun getBurgers(): Call<ArrayList<BurgerModel>>

    @GET("listburger/{ID}")
    fun getBurger(
        @Path("ID") ID: Int
    ): Call<ArrayList<BurgerModel>>

    @FormUrlEncoded
    @POST("listburger")
    fun createBurgers(
        @Field("Nama_Burger") Nama_Burger: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<BurgerModel>

    @FormUrlEncoded
    @PUT("listburger/{ID}")
    fun updateBurger(
        @Path("ID") ID: Int?,
        @Field("Nama_Burger") Nama_Burger: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<BurgerModel>

    @DELETE("listburger/{ID}")
    fun deleteBurger(
        @Path("ID") ID: Int?
    ): Call<Unit>


    /* Fungsi untuk tabel Pizza */
    @GET("listpizza")
    fun getPizzas(): Call<ArrayList<PizzaModel>>

    @GET("listpizza/{ID}")
    fun getPizza(
        @Path("ID") ID: Int
    ): Call<ArrayList<PizzaModel>>

    @FormUrlEncoded
    @POST("listpizza")
    fun createPizzas(
        @Field("Nama_Pizza") Nama_Pizza: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<PizzaModel>

    @FormUrlEncoded
    @PUT("listpizza/{ID}")
    fun updatePizza(
        @Path("ID") ID: Int?,
        @Field("Nama_Pizza") Nama_Pizza: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<PizzaModel>

    @DELETE("listpizza/{ID}")
    fun deletePizza(
        @Path("ID") ID: Int?
    ): Call<Unit>


    /* Fungsi untuk tabel Snack */
    @GET("listsnack")
    fun getSnacks(): Call<ArrayList<SnackModel>>

    @GET("listsnack/{ID}")
    fun getSnack(
        @Path("ID") ID: Int
    ): Call<ArrayList<SnackModel>>

    @FormUrlEncoded
    @POST("listsnack")
    fun createSnacks(
        @Field("Nama_Snack") Nama_Snack: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<SnackModel>

    @FormUrlEncoded
    @PUT("listsnack/{ID}")
    fun updateSnack(
        @Path("ID") ID: Int?,
        @Field("Nama_Snack") Nama_Snack: String,
        @Field("Harga") Harga: String,
        @Field("Deskripsi") Deskripsi: String,
    ): Call<SnackModel>

    @DELETE("listsnack/{ID}")
    fun deleteSnack(
        @Path("ID") ID: Int?
    ): Call<Unit>
}
