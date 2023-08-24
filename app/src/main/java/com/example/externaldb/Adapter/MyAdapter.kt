package com.example.externaldb.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.externaldb.R
import com.example.externaldb.ShayariDetailActivity
import com.example.externaldb.ShayariModel
import java.util.ArrayList

class   MyAdapter(private val context: Context, private val itemList: ArrayList<ShayariModel>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = itemList[position].shayari


        holder.itemView.setOnClickListener {
            var intent = Intent(context, ShayariDetailActivity::class.java)
            intent.putExtra("shayari",itemList[position].shayari)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }
}
