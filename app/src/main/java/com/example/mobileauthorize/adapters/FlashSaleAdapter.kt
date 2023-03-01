package com.example.mobileauthorize.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileauthorize.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class FlashSaleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val price = itemView.findViewById<TextView>(R.id.price)
    val name = itemView.findViewById<TextView>(R.id.name)
    val category = itemView.findViewById<TextView>(R.id.category)
    var image = itemView.findViewById<ImageView>(R.id.product_image)
    var discount = itemView.findViewById<TextView>(R.id.discount)
}

class FlashSaleAdapter(private val categoriesList: ArrayList<com.example.model.Product>):
    RecyclerView.Adapter<FlashSaleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_flash_sale, parent, false)
        return FlashSaleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        holder.price.text = "$ ${categoriesList[position].price.toString()}"
        MainScope().launch {
            var image: Bitmap?
            withContext(Dispatchers.IO) {
                val imageUrl = categoriesList[position].image_url
                val url = URL(imageUrl)
                try {
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val imageStream: InputStream = connection.inputStream
                    image = BitmapFactory.decodeStream(imageStream)
                }
                catch (e: Exception) {
                    image = null
                }
            }
            holder.image.setImageBitmap(image)
        }

        holder.name.text = categoriesList[position].name
        holder.category.text = categoriesList[position].category
        holder.discount.text = "${categoriesList[position].discount}% off"
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

}