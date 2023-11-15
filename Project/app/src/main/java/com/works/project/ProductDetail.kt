package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.works.project.configs.DB
import com.works.project.models.ProductModel

class ProductDetail : AppCompatActivity() {

    lateinit var db: DB
    lateinit var d_title: TextView
    lateinit var d_text: TextView
    lateinit var bigImage: ImageView
    lateinit var d_btnAddLike: Button

    companion object {
        var item: ProductModel? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        db = DB(this)
        bigImage = findViewById(R.id.bigImage)
        d_title = findViewById(R.id.d_title)
        d_text = findViewById(R.id.d_text)
        d_btnAddLike = findViewById(R.id.d_btnAddLike)

        if ( item != null ) {
            Log.d( "item", item.toString() )
            d_title.text = item!!.title
            Glide.with(this).load(item!!.images[0]).into(bigImage)
            d_text.text = item!!.description
        }

        d_btnAddLike.setOnClickListener {
           val status =  db.addLike(item!!.id, item!!.title)
            if (status > 0) {
                Toast.makeText(this, "Like Add Success", Toast.LENGTH_SHORT).show()
            }
        }

    }


}