package com.example.shoppingliststartcodekotlin.data
import com.google.firebase.firestore.Exclude


data class Product(
        var freeze_item: String = "",
        var item_qty: String = "",
        @get:Exclude var id: String = ""
) {


}