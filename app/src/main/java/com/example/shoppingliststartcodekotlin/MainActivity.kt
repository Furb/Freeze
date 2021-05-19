package com.example.shoppingliststartcodekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.example.shoppingliststartcodekotlin.adapters.ProductAdapter
import com.example.shoppingliststartcodekotlin.data.Product
import com.example.shoppingliststartcodekotlin.data.Repository
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //you need to have an Adapter for the products
    lateinit var adapter: ProductAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)



        btnAddToFreezer.setOnClickListener{
            val freeze_item = findViewById<EditText>(R.id.input_freeze_item).text.toString()
            val item_qty = findViewById<EditText>(R.id.input_freeze_qty).text.toString()
            val product = Product(freeze_item, item_qty)

            Repository.addProduct(product).observe(this, Observer {
                Log.d("Products", "Found ${it.size} products")
                updateUI()

            })
        }

        Repository.getData().observe(this, Observer {
            Log.d("Products","Found ${it.size} products")
            updateUI()
        })

        dialogButtons()
    }

    /* Menu begin*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> Toast.makeText(this, "Share me", Toast.LENGTH_SHORT).show()
            R.id.alarm -> {
                createAlarm("Remove from freezer", 21, 30)
            }
            R.id.clear_all -> {
                deleteAllDialog()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }/* Menu End*/

/**/



    /*
    ******** Dialogs **********
    */


    private fun dialogButtons() {


        filter.setOnClickListener {

            displaySuccessDialog("filter")

        }

    }

    private fun deleteAllDialog() {

        val areYouSureCallback = object: AreYouSureCallback {
            override fun proceed() {
                Repository.products.clear()
                adapter?.notifyDataSetChanged()
                displayToast("Everything is now defrosted")
            }

            override fun cancel() {
                displayToast("Everything is kept in the freezer")
            }
        }

        areYouSureDialog(
            "Do you want to remove all items from the freezer? Everything will defrost",
            areYouSureCallback
        )

    }

    fun displayToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun displaySuccessDialog(message: String?) {
        MaterialDialog(this)
                .show {
                    title(R.string.text_success)
                    message(text = message)
                    positiveButton (R.string.text_ok)
        }
    }

    fun addProductDialog(message: String?) {
        MaterialDialog(this)
                .show{
                    title(R.string.text_success)
                    message(text = message)
                    positiveButton (R.string.text_ok)
                }
    }

    fun editProductDialog(message: String?) {
        MaterialDialog(this)
            .show{
                title(R.string.text_success)
                message(text = message)
                positiveButton (R.string.text_ok)
            }
    }


    fun areYouSureDialog(message: String?, callback: AreYouSureCallback) {
        MaterialDialog(this)
                .show {
                    title(R.string.are_you_sure)
                    message(text = message)
                    negativeButton(R.string.text_cancel) {
                        callback.cancel()
                    }
                    positiveButton(R.string.text_yes) {
                        callback.proceed()
                    }
                }
    }


    interface AreYouSureCallback {

        fun proceed()

        fun cancel()

    }
    /*
     - End dialogs
    */





    /* Creates a preset alarm, to remember to remove items
  from freezer the night before */
    private fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            finish()
        }

    }


    fun updateUI() {
        val layoutManager = LinearLayoutManager(this)

        /*you need to have a defined a recyclerView in your
        xml file - in this case the id of the recyclerview should
        be "recyclerView" - as the code line below uses that */

        recyclerView.layoutManager = layoutManager

       adapter = ProductAdapter(Repository.products)

      /*connecting the recyclerview to the adapter  */
       recyclerView.adapter = adapter

    }


}