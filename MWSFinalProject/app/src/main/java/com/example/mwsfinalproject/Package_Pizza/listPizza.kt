package com.example.mwsfinalproject.Package_Pizza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mwsfinalproject.Package_Burger.ActivityAddBurger
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPizza : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pizza)

        supportActionBar?.setTitle("List Pizza")
        refreshLayout()
        getPizzaData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.add_item -> {
                val intent = Intent(this, AddPizza::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshLayout() {
        val refresh_Layout: SwipeRefreshLayout = findViewById(R.id.refresh_Layout)


        refresh_Layout.setOnRefreshListener {
            Toast.makeText(this, "Data Refreshed", Toast.LENGTH_SHORT).show()
            refresh_Layout.isRefreshing = false
            finish()
            overridePendingTransition(0, 0)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }

    private fun getPizzaData() {

        val listData = ArrayList<PizzaModel>()

        val rvStudentData: RecyclerView = findViewById(R.id.rvPizza)
        rvStudentData.setHasFixedSize(true) /* RecyclerView sudah memiliki ukuran yang fix */
        rvStudentData.layoutManager = LinearLayoutManager(this) /* layoutManager ???, tidak tau */

        val APIClient =
            APIClient.create()          /* variabel menghubungi object APIClient dan mengambil fungsi create() */
        val callData =
            APIClient.getPizzas()

        callData.enqueue(object : Callback<ArrayList<PizzaModel>> {
            override fun onResponse(
                call: Call<ArrayList<PizzaModel>>,
                response: Response<ArrayList<PizzaModel>>
            ) {

                val data = response.body() /* Mengambil response dari body API */

                data?.let { listData.addAll(it) }

                val adapterData = AdapterPizza(listData)

                rvStudentData.adapter = adapterData

                rvStudentData.setOnClickListener {
                    Toast.makeText(this@ListPizza, "hello dasda", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<PizzaModel>>, t: Throwable) {
                Log.e("Data", t.message.toString())
            }

        })
    }
}