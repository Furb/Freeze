package com.example.shoppingliststartcodekotlin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object Repository {

    var products = mutableListOf<Product>()
    private val db = Firebase.firestore



    //listener to changes that we can then use in the Activity
    private var productListener = MutableLiveData<MutableList<Product>>()


    fun getData(): MutableLiveData<MutableList<Product>> {
        if (products.isEmpty()) {
            createTestData()
        }

        else {
            readDataFromFireBase()
        }

        productListener.value = products //we inform the listener we have new data
        return productListener
    }



    fun addProduct(product: Product): MutableLiveData<MutableList<Product>> {
        productListener.value = products
        products.add(product)
        db.collection("products")
            .add(product) //will automatically assign an ID
            .addOnSuccessListener { documentReference ->
                Log.d("Error", "DocumentSnapshot written with ID: " +
                        documentReference.id)
                product.id = documentReference.id  //assign new ID to the class
            }
            .addOnFailureListener { e -> Log.w("Error", "Error adding document", e)                   } //end failure listener

        return productListener
    } //end method



    fun deleteProduct(index: Int): MutableLiveData<MutableList<Product>> {
        products.removeAt(index)
        productListener.value = products
        return productListener
    }


    private fun readDataFromFireBase()
    {
        val db = Firebase.firestore
        db.collection("products").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Repository", "${document.id} => ${document.data}")
                    val product = document.toObject<Product>()
                    product.id = document.id  //set the ID in the product class
                    products.add(product)
                }
                productListener.value = products //notify our listener we have new data
            }
            .addOnFailureListener { exception ->
                Log.d("Repository", "Error getting documents: ", exception)
            }
    }


    // So instead of calling createTestData below you could call this method

    fun createTestData() // Only runs if there are no items in the the freezer.
    {
        products.add(
                Product(
                    "kylling",
                    "2stk",
                        )

        )
        products.add(
                Product(
                    "Rarbaber",
                    "800g",
                )

        )
        products.add(
                Product(
                    "Lakseside",
                    "800g",
                )

        )
        products.add(
                Product(
                    "Isterninger",
                    "1 pose",
                )

        )


    }  // End test data



}  //End object repository