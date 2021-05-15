package com.example.shoppingliststartcodekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.R
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.example.shoppingliststartcodekotlin.data.Repository.products


class ProductAdapter(var products: MutableList<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

   //private var frozenItem = arrayOf("Kylling", "Rarbarber", "Lasagne", "Isterninger", "Kanelsnegle")

    //private var quantity = arrayOf("2kg", "800g", "1 stk", "3 poser", "1 poser")

    //private var status = intArrayOf(R.drawable.ic_outline_circle_16, R.drawable.ic_outline_circle_16, R.drawable.ic_outline_circle_16, R.drawable.ic_outline_circle_16,R.drawable.ic_outline_circle_16)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.freezer_item_layout, parent,false)
        return ViewHolder(v)
    }



    fun addItem(view: View) {

        Repository.products.add(Product(
            notifyItemInserted(Repository.products.size -1).toString()
        ))

    }



    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {

        holder.freezeItem.text = products[position].freeze_item
        holder.itemQty.text = products[position].item_qty
        //holder.itemStatus.setImageResource(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //here you need to to do stuff also - to back to the exercises
        //about recyclerviews and you can use approach that were used
        //in the exercise about recyclerviews from the book (lesson 3)
        //if you did not do that exercise - then first do that exercise in
        //a seperate project

        var itemStatus: ImageView
        var freezeItem: TextView
        var itemQty: TextView

        init {
            itemStatus = itemView.findViewById(R.id.status_image)
            freezeItem = itemView.findViewById(R.id.freeze_item)
            itemQty = itemView.findViewById(R.id.item_qty)


            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Click me, now do something", Toast.LENGTH_LONG).show()
            }

        }

    }
}
