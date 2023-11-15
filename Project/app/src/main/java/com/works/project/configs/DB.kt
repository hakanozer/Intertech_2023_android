package com.works.project.configs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.works.project.models.DBProduct

class DB (context: Context) : SQLiteOpenHelper(context, "project.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val likesSql = "CREATE TABLE \"likes\" (\n" +
                "\t\"lid\"\tINTEGER,\n" +
                "\t\"pid\"\tINTEGER UNIQUE,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"lid\" AUTOINCREMENT)\n" +
                ");"
        db?.execSQL(likesSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val likesSql = "drop table if exists likes"
        db?.execSQL(likesSql)
        onCreate(db)
    }

    fun addLike( pid: Long, title: String ) : Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("pid", pid)
        values.put("title", title)
        val effectRow = db.insert("likes", null, values)
        db.close()
        return effectRow
    }

    fun removeLike( lid: Int ) : Int {
        val db = this.writableDatabase
        val status = db.delete("likes", "lid = $lid", null)
        db.close()
        return status
    }

    fun allLikes() : List<DBProduct> {
        val arr =  mutableListOf<DBProduct>()
        val db = this.readableDatabase
        val cursor = db.query("likes", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val pro = DBProduct(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2)
            )
            arr.add(pro)
        }
        return arr
    }

}