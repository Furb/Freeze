package com.example.shoppingliststartcodekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.shoppingliststartcodekotlin.R
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.products


class ProductAdapter(var products: MutableList<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.freezer_item_layout, parent,false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.freezeItem.text = products[position].freeze_item
        holder.itemQty.text = products[position].item_qty
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //here you need to to do stuff also - to back to the exercises
        //about recyclerview and you can use approach that were used
        //in the exercise about recyclerview from the book (lesson 3)
        //if you did not do that exercise - then first do that exercise in
        //a separate project

        var itemStatus: ImageView
        var freezeItem: TextView
        var itemQty: TextView

        init {
            itemStatus = itemView.findViewById(R.id.status_image)
            freezeItem = itemView.findViewById(R.id.freeze_item)
            itemQty = itemView.findViewById(R.id.item_qty)


            //Click on individual freeze item. Should open dialog
            itemView.setOnClickListener {

                Toast.makeText(
                        itemView.context,
                        "Click me, now do something",
                        Toast.LENGTH_SHORT).show()
            }

        }

    }
}