package com.example.my_khatabook.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_khatabook.ModalClass.CategoryModal
import com.example.my_khatabook.R

class Category_Adaptor(val context: Context, val categoryList: ArrayList<CategoryModal>) :
    RecyclerView.Adapter<Category_Adaptor.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var catetgoryStyle: TextView = itemView.findViewById(R.id.categoryStyle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_style, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.catetgoryStyle.text = categoryList.get(position).name
    }
}
