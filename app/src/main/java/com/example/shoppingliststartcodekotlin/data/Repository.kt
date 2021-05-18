package com.example.shoppingliststartcodekotlin.data

import android.view.View
import androidx.lifecycle.MutableLiveData

object Repository {
    var products = mutableListOf<Product>()

    //listener to changes that we can then use in the Activity
    private var productListener = MutableLiveData<MutableList<Product>>()


    fun getData(): MutableLiveData<MutableList<Product>> {
        if (products.isEmpty())
            createTestData()
        productListener.value = products //we inform the listener we have new data
        return productListener
    }



    fun addProduct(product: Product): MutableLiveData<MutableList<Product>> {
        productListener.value = products
        products.add(product)

        return productListener
    }


    fun deleteProduct(index: Int): MutableLiveData<MutableList<Product>> {
        products.removeAt(index)
        productListener.value = products
        return productListener
    }


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
                    "'Isterninger",
                    "1 pose",
                )

        )


    }  // End test data



}  //End object repository