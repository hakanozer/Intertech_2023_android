package com.works.project

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.works.project.configs.DB
import com.works.project.models.DBProduct

class Likes : AppCompatActivity() {

    lateinit var db: DB
    lateinit var listView: ListView
    lateinit var arr: List<DBProduct>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_likes)
        db = DB(this)
        listView = findViewById(R.id.listView)
        dbResult()

        listView.setOnItemLongClickListener { parent, view, position, id ->

            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Are you sure!")
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                val item = arr.get(position)
                val status = db.removeLike(item.lid)
                if (status > 0) {
                    dbResult()
                }
            })
            val alertDialog = alert.create()
            alertDialog.show()

            true
        }

    }

    fun dbResult() {
        arr = db.allLikes()
        var titleArr = arrayListOf<String>()
        arr.forEach {
            titleArr.add(it.title)
        }
        val adaptor = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, titleArr)
        listView.adapter = adaptor
    }

}