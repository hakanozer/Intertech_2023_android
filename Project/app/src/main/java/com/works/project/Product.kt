package com.works.project

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.works.project.adapters.ProductAdapter
import com.works.project.configs.ApiClient
import com.works.project.models.ProductModel
import com.works.project.models.ProductsModel
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Product : AppCompatActivity() {

    lateinit var dummyService: DummyService
    lateinit var listView: ListView
    var arr = listOf<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        listView = findViewById(R.id.listView)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        dummyService.allProduct().enqueue(object : Callback<ProductsModel> {
            override fun onResponse(call: Call<ProductsModel>, response: Response<ProductsModel>) {
                val status = response.code()
                if (status == 200) {
                    val productsModel = response.body()
                    if (productsModel != null) {
                         arr = productsModel.products
                        //productAdapter.notifyDataSetChanged()
                        val productAdapter = ProductAdapter(this@Product, arr)
                        listView.adapter = productAdapter
                    }
                }
            }
            override fun onFailure(call: Call<ProductsModel>, t: Throwable) {
                Toast.makeText(this@Product, "Server Error!", Toast.LENGTH_SHORT).show()
            }
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            //view.setBackgroundColor(Color.RED)
            val item = arr.get(position)
            ProductDetail.item = item
            val intent = Intent(this, ProductDetail::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itm_likes) {
            val intent = Intent(this@Product, Likes::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.itm_logout) {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("jwt")
            editor.commit()
            val intent = Intent(this@Product, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true
    }

}