package com.example.mwsfinalproject.Package_Snack

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
import com.example.restapiui.SnackModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterSnack(private val datalist: ArrayList<SnackModel>) :
    RecyclerView.Adapter<AdapterSnack.ViewHolderData>() {
    class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {


        /* ItemView terhubung ke layout_list_snack */
        val tvID = itemView.findViewById<TextView>(R.id.tvID)
        val tvNamaSnack = itemView.findViewById<TextView>(R.id.tvNamaSnack)
        val tvHargaSnack = itemView.findViewById<TextView>(R.id.tvHargaSnack)
        val tvDeskripsiSnack = itemView.findViewById<TextView>(R.id.tvDeskripsiSnack)

        val context = itemView.context
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderData {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_snack, parent, false)

        return ViewHolderData(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val item = datalist[position]
        val ctx = holder.itemView.context
        val snackTerpilih = holder.itemView

        snackTerpilih.setOnClickListener { //Action Saat Recycler view/view terpilih(saat salah satu object terpilih)
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
                        val updateSnack = APIClient.updateSnack(
                            ID = (tvID.text as String?)?.toInt(),
                            Nama_Snack = edProduk.text.toString(),
                            Harga = edHarga.text.toString(),
                            Deskripsi = edDeskripsi.text.toString(),
                        )
                        updateSnack.enqueue(object : Callback<SnackModel> {
                            override fun onResponse(
                                call: Call<SnackModel>,
                                response: Response<SnackModel>
                            ) {
                                Toast.makeText(ctx, "Data Berhasil Diupdate", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onFailure(call: Call<SnackModel>, t: Throwable) {
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

                val deleteSnack = APIClient.deleteSnack(ID = tvID)

                deleteSnack.enqueue(object : Callback<Unit> {
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

            Toast.makeText(ctx, "${item.Nama_Snack} ${item.ID}", Toast.LENGTH_SHORT)
                .show() // Menampilkan ID dari view yang dipilih
        }
        holder.tvID.text = item.ID
        holder.tvNamaSnack.text = item.Nama_Snack
        holder.tvHargaSnack.text = item.Harga
        holder.tvDeskripsiSnack.text = item.Deskripsi
    }

    override fun getItemCount(): Int = datalist.size
}
