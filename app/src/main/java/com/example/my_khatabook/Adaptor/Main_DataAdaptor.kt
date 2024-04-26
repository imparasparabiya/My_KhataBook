package com.example.my_khatabook.Adaptor

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.my_khatabook.Entries_add_Activity
import com.example.my_khatabook.ModalClass.EntriesAdd_Modal
import com.example.my_khatabook.R

class Main_DataAdaptor(val context: Context, val list: ArrayList<EntriesAdd_Modal>) :
    RecyclerView.Adapter<Main_DataAdaptor.MainDataHolder>() {

    class MainDataHolder(itemview: View) : ViewHolder(itemview) {
        var Title_Style = itemview.findViewById<TextView>(R.id.Title_Style)
        var Date_Style = itemview.findViewById<TextView>(R.id.Date_Style)
        var Note_Style = itemview.findViewById<TextView>(R.id.Note_Style)
        var Amount_Style = itemview.findViewById<TextView>(R.id.Amount_Style)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainDataHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.main_data_style, parent, false)

        return MainDataHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainDataHolder, position: Int) {
        if (list[position].status == 0) {
            holder.Amount_Style.setTextColor(Color.GREEN)
        } else {
            holder.Amount_Style.setTextColor(Color.RED)
        }
        holder.Title_Style.text = list[position].title
        holder.Amount_Style.text = list[position].amount.toString()
        holder.Note_Style.text = list[position].note
        holder.Date_Style.text = list[position].date

        holder.Amount_Style.setOnClickListener {

            var intent = Intent(context, Entries_add_Activity::class.java)

            intent.putExtra("id", list[position].id)
            intent.putExtra("title", list[position].title)
            intent.putExtra("amount", list[position].amount)
            intent.putExtra("date", list[position].date)
            intent.putExtra("note", list[position].note)
            intent.putExtra("status", list[position].status)
            intent.putExtra("category", list[position].category)

            context.startActivity(intent)
        }
    }
}