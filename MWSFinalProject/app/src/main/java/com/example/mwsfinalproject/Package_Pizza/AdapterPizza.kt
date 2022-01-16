package com.example.mwsfinalproject.Package_Pizza

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsfinalproject.R
import com.example.restapiui.API.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterPizza(private val datalist: ArrayList<PizzaModel>) :
    RecyclerView.Adapter<AdapterPizza.ViewHolderData>() {
    class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var onItemClick:(())

        /* ItemView terhubung ke layout_list_pizza */
        val tvID = itemView.findViewById<TextView>(R.id.tvID)
        val tvNamaPizza = itemView.findViewById<TextView>(R.id.tvNamaPizza)
        val tvHargaPizza = itemView.findViewById<TextView>(R.id.tvHargaPizza)
        val tvDeskripsiPizza = itemView.findViewById<TextView>(R.id.tvDeskripsiPizza)

        //        val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
//        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val context = itemView.context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderData {
        /* Mengambil konten dari layout layout_list_pizza */
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_pizza, parent, false)

        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        /* Mengikat text dari textView ke data didalam dataList/PizzaModel berdasarkan ID/posisi */
        val item = datalist[position]
        val ctx = holder.itemView.context
        val pizzaTerpilih = holder.itemView

        pizzaTerpilih.setOnClickListener { //Action Saat Recycler view/view terpilih(saat salah satu object terpilih)
            // variabel untuk layout Option
            val dialogView = LayoutInflater.from(ctx).inflate(R.layout.layout_option, null)
            val mBuilder = AlertDialog.Builder(ctx).setView(dialogView)
            val alertDialog = mBuilder.show()
            val btnEdit = alertDialog.findViewById<Button>(R.id.btnEdit)
            val btnHapus = alertDialog.findViewById<Button>(R.id.btnHapus)
            val btnCancel = alertDialog.findViewById<Button>(R.id.btnCancel)


            // variabel untuk layout Edit
            val layoutEdit = LayoutInflater.from(ctx).inflate(R.layout.layout_edit, null)
            val tvID = layoutEdit.findViewById<TextView>(R.id.tvID)
            val edProduk = layoutEdit.findViewById<EditText>(R.id.edProduk)
            val edHarga = layoutEdit.findViewById<EditText>(R.id.edHarga)
            val edDeskripsi = layoutEdit.findViewById<EditText>(R.id.edDeskripsi)

            val btnSubmit = layoutEdit.findViewById<Button>(R.id.btnSubmit)
            val btnCancelEdit = layoutEdit.findViewById<Button>(R.id.btnCancel)

            tvID.text = item.ID

            btnEdit.setOnClickListener { // Menampilkan layout edit dalam bentuk alert dialog
                val mBuilderEdit = AlertDialog.Builder(ctx).setView(layoutEdit).setTitle("Edit")
                val alertDialogEdit = mBuilderEdit.show()


                btnSubmit.setOnClickListener {
                    val APIClient =
                        APIClient.create()          /* variabel menghubungi object APIClient dan mengambil fungsi create() */

                    val tvID = tvID
                    val edProduk = edProduk
                    val edHarga = edHarga
                    val edDeskripsi = edDeskripsi


                    if (edProduk.text.isNotEmpty() && edHarga.text.isNotEmpty()) {
                        val updatePizza = APIClient.updatePizza(
                            ID = (tvID.text as String?)?.toInt(),
                            Nama_Pizza = edProduk.text.toString(),
                            Harga = edHarga.text.toString(),
                            Deskripsi = edDeskripsi.text.toString(),
                        )
                        updatePizza.enqueue(object : Callback<PizzaModel> {
                            override fun onResponse(
                                call: Call<PizzaModel>,
                                response: Response<PizzaModel>
                            ) {
                                Toast.makeText(ctx, "Data Berhasil Diupdate", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onFailure(call: Call<PizzaModel>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                        alertDialogEdit.dismiss()
                        alertDialog.dismiss()
                    } else {
                        edProduk.error = "Field Tidak Boleh Kosong"
                        edHarga.error = "Field Tidak Boleh Kosong"
                    }
                }

                btnCancelEdit.setOnClickListener {
                    alertDialogEdit.dismiss()
                    alertDialog.dismiss()
                }
            }

            btnHapus.setOnClickListener {
                val layout_confirmation =
                    LayoutInflater.from(ctx).inflate(R.layout.layout_confirmation, null)
                val mBuilder = AlertDialog.Builder(ctx).setView(layout_confirmation)
                val confirmDialog = mBuilder.show()

                val APIClient =
                    APIClient.create()
                val tvID = (tvID.text as String?)?.toInt()

                val deletePizza = APIClient.deletePizza(ID = tvID)

                deletePizza.enqueue(object : Callback<Unit> {
                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>
                    ) {
                        Toast.makeText(ctx, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
                confirmDialog.dismiss()
                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener {
                alertDialog.dismiss()
            }

            Toast.makeText(ctx, "${item.Nama_Pizza} ${item.ID}", Toast.LENGTH_SHORT)
                .show() // Menampilkan ID dari view yang dipilih
        }
        holder.tvID.text = item.ID
        holder.tvNamaPizza.text = item.Nama_Pizza
        holder.tvHargaPizza.text = item.Harga
        holder.tvDeskripsiPizza.text = item.Deskripsi

    }

    override fun getItemCount(): Int = datalist.size
}