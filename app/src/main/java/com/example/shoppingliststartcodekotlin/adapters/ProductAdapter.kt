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
import com.google.android.material.snackbar.Snackbar


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
        var deleteItem: ImageView

        init {
            itemStatus = itemView.findViewById(R.id.status_image)
            freezeItem = itemView.findViewById(R.id.freeze_item)
            itemQty = itemView.findViewById(R.id.item_qty)
            deleteItem = itemView.findViewById(R.id.btn_Delete_Item)


            //Click on individual freeze item. Should open an edit dialog
            itemView.setOnClickListener {

                Toast.makeText(
                    itemView.context,
                    "Should go to Edit dialog",
                    Toast.LENGTH_SHORT
                ).show()

                 deleteItem.setOnClickListener {

                    val position: Int = adapterPosition
                    Repository.deleteProduct(position)
                    notifyItemRemoved(position)
                     Snackbar.make( itemView, "Remove this item from the freezer", Snackbar.LENGTH_LONG ).setAction("Undo", null).show()


                }

            }


        }


    }
}