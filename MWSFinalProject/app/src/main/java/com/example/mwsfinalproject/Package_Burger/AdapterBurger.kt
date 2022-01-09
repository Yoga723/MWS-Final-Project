package com.example.mwsfinalproject.Package_Burger

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsfinalproject.R

class AdapterBurger(private val datalist: ArrayList<BurgerModel>) :
    RecyclerView.Adapter<AdapterBurger.ViewHolderData>() {
    class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /* ItemView terhubung ke layout_list_burger */
        val tvID = itemView.findViewById<TextView>(R.id.tvID)
        val tvNamaBurger = itemView.findViewById< TextView>(R.id.tvNamaBurger)
        val tvHargaBurger = itemView.findViewById< TextView>(R.id.tvHargaBurger)
        val tvDeskripsiBurger = itemView.findViewById< TextView>(R.id.tvDeskripsiBurger)
        val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)
        val context = itemView.context
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterBurger.ViewHolderData {
        /* Mengambil konten dari layout layout_list_burger */
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_burger, parent, false)

        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: AdapterBurger.ViewHolderData, position: Int) {
        /* Mengikat text dari textView ke data didalam dataList/StudentModel berdasarkan ID/posisi */

        val item = datalist[position]

        holder.tvID.text = item.ID
        holder.tvNamaBurger.text = item.Nama_Burger
        holder.tvHargaBurger.text = item.Harga
        holder.tvDeskripsiBurger.text = item.Deskripsi
        holder.ivEdit.setOnClickListener { /* itemView = tampilannya / dalam kasus ini tampilan card viewnya */
            val ctx = holder.context
            Toast.makeText(ctx, "hello", Toast.LENGTH_SHORT).show()
//            val intent = Intent(ctx, activity_student_edit::class.java)
//            intent.putExtra("ID", item.ID)
//            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = datalist.size
}