package com.example.mwsfinalproject.Package_Snack

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
import com.example.mwsfinalproject.Package_Pizza.AddPizza
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import com.example.restapiui.SnackModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listSnack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_snack)

        supportActionBar?.setTitle("List Snack")
        refreshLayout()
        getSnackData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.add_item -> {
                val intent = Intent(this, AddSnack::class.java)
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

    private fun getSnackData() {

        val listData = ArrayList<SnackModel>()

        val rvStudentData: RecyclerView = findViewById(R.id.rvSnack)
        rvStudentData.setHasFixedSize(true) /* RecyclerView sudah memiliki ukuran yang fix */
        rvStudentData.layoutManager = LinearLayoutManager(this) /* layoutManager ???, tidak tau */

        val APIClient =
            APIClient.create()          /* variabel menghubungi object APIClient dan mengambil fungsi create() */
        val callData =
            APIClient.getSnacks()

        callData.enqueue(object : Callback<ArrayList<SnackModel>> {
            override fun onResponse(
                call: Call<ArrayList<SnackModel>>,
                response: Response<ArrayList<SnackModel>>
            ) {

                val data = response.body() /* Mengambil response dari body API */

                data?.let { listData.addAll(it) }

                val adapterData = AdapterSnack(listData)

                rvStudentData.adapter = adapterData

                rvStudentData.setOnClickListener {
                    Toast.makeText(this@listSnack, "hello dasda", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<SnackModel>>, t: Throwable) {
                Log.e("Data", t.message.toString())
            }

        })
    }
}