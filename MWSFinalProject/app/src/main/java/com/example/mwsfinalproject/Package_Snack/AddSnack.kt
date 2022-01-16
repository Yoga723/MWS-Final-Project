package com.example.mwsfinalproject.Package_Snack

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mwsfinalproject.Package_Pizza.PizzaModel
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import com.example.restapiui.SnackModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSnack : AppCompatActivity() {
    lateinit var edProduk: EditText
    lateinit var edHarga: EditText
    lateinit var edDeskripsi: EditText
    lateinit var btnCancel: Button
    lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_snack)
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

        btnSubmit.setOnClickListener {
            val APIClient =
                APIClient.create()

            val createSnacks = APIClient.createSnacks(
                Nama_Snack = edProduk.text.toString(),
                Harga = edHarga.text.toString(),
                Deskripsi = edDeskripsi.text.toString(),
            )

            createSnacks.enqueue(object : Callback<SnackModel> {
                override fun onResponse(call: Call<SnackModel>, response: Response<SnackModel>) {
                    Toast.makeText(
                        this@AddSnack,
                        "Data berhasil ditambahkan, harap refresh",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

                override fun onFailure(call: Call<SnackModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
}