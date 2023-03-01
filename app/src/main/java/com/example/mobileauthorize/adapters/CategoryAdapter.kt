package com.example.mobileauthorize.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mobileauthorize.R

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameText = itemView.findViewById<TextView>(R.id.category_name)
    var image = itemView.findViewById<ImageView>(R.id.ic_category)
}

class CategoryAdapter(private val categoriesList: ArrayList<com.example.model.Category>, val resources: Resources):
    Adapter<MyViewHolder>() {

    val saveInstanceList = ArrayList<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        val myViewHolder = MyViewHolder(itemView)
        saveInstanceList.add(itemView)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameText.text = categoriesList[position].name
        holder.image.setImageDrawable(resources.getDrawable(categoriesList[position].image))
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }


}