package com.example.shoppingliststartcodekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingliststartcodekotlin.adapters.ProductAdapter
import com.example.shoppingliststartcodekotlin.data.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //you need to have an Adapter for the products
 
   private var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Repository.getData().observe(this, Observer {
            Log.d("Products","Found ${it.size} products")
            updateUI()
        })
    }

    /* Menu begin*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.share -> Toast.makeText(this, "Share me", Toast.LENGTH_SHORT).show()
            R.id.alarm -> {
                createAlarm("Remove from freezer", 21, 30)
            }
            R.id.deleteAll -> Toast.makeText(this, "Delete all", Toast.LENGTH_SHORT).show()
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


    /* Menu End*/

    /* Creates a predefined alarm, to remember to remove items
     from freezer the night before */
    fun createAlarm(message: String, hour: Int, minutes: Int) {
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