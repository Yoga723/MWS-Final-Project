package com.example.mwsfinalproject.Package_Burger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListBurger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_burger)

        getSupportActionBar()?.setTitle("List Burger")
        refreshLayout()
        getBurgerData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.add_item -> {
                val intent = Intent(this, ActivityAddBurger::class.java)
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

    private fun getBurgerData() {

        val listData = ArrayList<BurgerModel>()

        val rvStudentData: RecyclerView = findViewById(R.id.rvBurger)
        rvStudentData.setHasFixedSize(true) /* RecyclerView sudah memiliki ukuran yang fix */
        rvStudentData.layoutManager = LinearLayoutManager(this) /* layoutManager ???, tidak tau */

        val APIClient =
            APIClient.create()          /* variabel menghubungi object APIClient dan mengambil fungsi create() */
        val callData =
            APIClient.getBurgers()

        callData.enqueue(object : Callback<ArrayList<BurgerModel>> {
            override fun onResponse(
                call: Call<ArrayList<BurgerModel>>,
                response: Response<ArrayList<BurgerModel>>
            ) {

                val data = response.body() /* Mengambil response dari body API */

                data?.let { listData.addAll(it) } /* masih belum mengerti */

                val adapterData = AdapterBurger(listData)

                rvStudentData.adapter = adapterData

                rvStudentData.setOnClickListener {
                    Toast.makeText(this@ListBurger, "hello dasda", Toast.LENGTH_SHORT).show()
                }

                Log.d("data", adapterData.datalist.toString())
            }

            override fun onFailure(call: Call<ArrayList<BurgerModel>>, t: Throwable) {
                Log.e("Data", t.message.toString())
            }

        })
    }
}