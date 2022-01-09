package com.example.mwsfinalproject.Package_Burger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListBurger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_burger)

        getBurgerData()

    }

    private fun getBurgerData() {

        val listData = ArrayList<BurgerModel>() /* listData yaitu list array dari StudentModel*/

        val rvStudentData: RecyclerView = findViewById(R.id.rvBurger)
        rvStudentData.setHasFixedSize(true) /* RecyclerView sudah memiliki ukuran yang fix */
        rvStudentData.layoutManager = LinearLayoutManager(this) /* layoutManager ???, tidak tau */

        val APIClient =
            APIClient.create()          /* variabel menghubungi object APIClient dan mengambil fungsi create() */
        val callData =
            APIClient.getBurgers()      /* callData pergi ke object APIClient dan kemudian ke APIInterface dan mengambil fungsi getStudents() */

        callData.enqueue(object : Callback<ArrayList<BurgerModel>> {
            override fun onResponse(
                call: Call<ArrayList<BurgerModel>>,
                response: Response<ArrayList<BurgerModel>>
            ) {

                val data = response.body() /* Mengambil response dari body API */

                data?.let { listData.addAll(it) } /* masih belum mengerti */

                val adapterData = AdapterBurger(listData)

                rvStudentData.adapter = adapterData
            }

            override fun onFailure(call: Call<ArrayList<BurgerModel>>, t: Throwable) {
                Log.e("Data", t.message.toString())
            }

        })
    }
}