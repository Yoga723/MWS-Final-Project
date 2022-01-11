package com.example.mwsfinalproject.Package_Burger

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsfinalproject.R

class AdapterBurger(private val datalist: ArrayList<BurgerModel>) :
    RecyclerView.Adapter<AdapterBurger.ViewHolderData>() {
    class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var onItemClick:(())

        /* ItemView terhubung ke layout_list_burger */
        val tvID = itemView.findViewById<TextView>(R.id.tvID)
        val tvNamaBurger = itemView.findViewById<TextView>(R.id.tvNamaBurger)
        val tvHargaBurger = itemView.findViewById<TextView>(R.id.tvHargaBurger)
        val tvDeskripsiBurger = itemView.findViewById<TextView>(R.id.tvDeskripsiBurger)
//        val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
//        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val context = itemView.context

//        init {
//            itemView.setOnClickListener {
//                onItemClick?.invoke(datalist[adapterPosition])
//            }
//        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderData {
        /* Mengambil konten dari layout layout_list_burger */
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_burger, parent, false)

        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        /* Mengikat text dari textView ke data didalam dataList/BurgerModel berdasarkan ID/posisi */
        val item = datalist[position]
        val ctx = holder.itemView.context
        val burgerTerpilih = holder.itemView

        burgerTerpilih.setOnClickListener { //Action Saat Recycler view/view terpilih(saat salah satu object terpilih)
            val dialogView = LayoutInflater.from(ctx).inflate(R.layout.layout_edit, null)
            val mBuilder = AlertDialog.Builder(ctx).setView(dialogView).setTitle("Select Action")
            val alertDialog = mBuilder.show()

            val btnEdit = alertDialog.findViewById<Button>(R.id.btnEdit)
            val btnHapus = alertDialog.findViewById<Button>(R.id.btnHapus)
            val btnCancel = alertDialog.findViewById<Button>(R.id.btnCancel)

            btnEdit.setOnClickListener {

            }

            btnCancel.setOnClickListener{
                alertDialog.dismiss()
            }

            Toast.makeText(ctx, "helloasdsadad ${item.ID}", Toast.LENGTH_SHORT).show() // Menampilkan ID dari view yang dipilih
        }
        holder.tvID.text = item.ID
        holder.tvNamaBurger.text = item.Nama_Burger
        holder.tvHargaBurger.text = item.Harga
        holder.tvDeskripsiBurger.text = item.Deskripsi

    }

    override fun getItemCount(): Int = datalist.size
}