package com.ppb.cardview_sqlite.connection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ppb.cardview_sqlite.R
import com.ppb.cardview_sqlite.UpdateActivity
import com.ppb.cardview_sqlite.model.User

class DBAdapter(private val listDataku: ArrayList<User>):
    RecyclerView.Adapter<DBAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var rvEmailku: TextView = itemView.findViewById(R.id.rv_emailku)
        var rvPasswordku: TextView = itemView.findViewById(R.id.rv_passwordku)
        var rvAkunku: TextView = itemView.findViewById(R.id.rv_akunku)
        var rvNamaku: TextView = itemView.findViewById(R.id.rv_namaku)
        var btnDelete: Button = itemView.findViewById(R.id.btn_deletecard)
        var btnUpdate: Button = itemView.findViewById(R.id.btn_updatecard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_dataku, parent, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataku = listDataku[position]
        holder.rvEmailku.text = dataku.email
        holder.rvPasswordku.text = dataku.password
        holder.rvAkunku.text = dataku.username
        holder.rvNamaku.text = dataku.fullname

        holder.btnDelete.setOnClickListener {
            var adapterDBHelper: DBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteData(dataku.email)
            listDataku.removeAt(position)
            notifyDataSetChanged()
        }

        holder.btnUpdate.setOnClickListener {
            val pindahUpdateActivity = Intent(holder.itemView.context, UpdateActivity::class.java)
            val bundle = Bundle()
            bundle.putString("emailku", dataku.email)
            bundle.putString("usernameku", dataku.username)
            bundle.putString("fullnameku", dataku.fullname)
            bundle.putString("passwordku", dataku.password)
            pindahUpdateActivity.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUpdateActivity)
        }
    }

    override fun getItemCount(): Int {
        return listDataku.size
    }

}