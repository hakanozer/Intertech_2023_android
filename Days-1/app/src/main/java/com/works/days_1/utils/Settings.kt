package com.works.days_1.utils

import android.util.Log

class Settings {

    val age = 100
    private val dbName = "project.sqlite"
    private val dbVersion = 1
    private val baseUrl = "https://dummyjson.com/"

    fun func1() {
        Log.d("TAG", "func1 Call")
    }

    fun dbConnect() {
        Log.d("dbName", dbName)
        Log.d("dbVersion", dbVersion.toString())
    }


    fun call( num1: Int, num2: Int ) : Int {
        return num1 + num2
    }

    fun action( num: Int?, data: String ) : Int {
        if ( num != null ) {
            val sum = num + data.count()
            return sum
        }
        return data.count()
    }

}