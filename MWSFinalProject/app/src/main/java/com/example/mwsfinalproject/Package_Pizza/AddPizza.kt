package com.example.mwsfinalproject.Package_Pizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPizza : AppCompatActivity() {
    lateinit var edProduk: EditText
    lateinit var edHarga: EditText
    lateinit var edDeskripsi: EditText
    lateinit var btnCancel: Button
    lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pizza)

        edProduk = findViewById(R.id.edProduk)
        edHarga = findViewById(R.id.edHarga)
        edDeskripsi = findViewById(R.id.edDeskripsi)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnCancel = findViewById(R.id.btnCancel)

        addProduk()

        btnCancel.setOnClickListener {
            finish()
        }
    }

    fun addProduk() {

        btnSubmit.setOnClickListener{
            val APIClient =
                APIClient.create()

            val createPizzas = APIClient.createPizzas(
                Nama_Pizza = edProduk.text.toString(),
                Harga = edHarga.text.toString(),
                Deskripsi = edDeskripsi.text.toString(),
            )

            createPizzas.enqueue(object : Callback<PizzaModel> {
                override fun onResponse(call: Call<PizzaModel>, response: Response<PizzaModel>) {
                    Toast.makeText(this@AddPizza, "Data berhasil ditambahkan, harap refresh", Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(call: Call<PizzaModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
}