package com.works.days_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.works.days_1.utils.AppEnum
import com.works.days_1.utils.Product
import com.works.days_1.utils.PropsProduct
import com.works.days_1.utils.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Değişkenler
        // var, val
        var name = "Erkan Bilsin"
        name = "Serkan Bilirim"

        val surname:String? = "Bilsinler"
        if (surname != null) {
            Log.d("Surname", surname)
        }


        val num4:Byte = 127
        val num3: Short = 40
        val age = 30
        val num5: Long = 50

        val num1 = 10.3
        val num2:Float = 10.3f

        //println(name)
        Log.d("Name", name)

        // Diziler
        val arr2 = arrayOf("","","","")
        val arr = mutableListOf<String>()
        arr.add("İstanbul")
        arr.add("Ankara")
        arr.add("Ankara")
        arr.add("Ankara")
        arr.add("İzmir")
        arr.add("İnegöl")
        arr.add("İnegöl")
        arr.add(0, "Hatay")

        // delete item
        arr.removeAt(2)
        Log.d("Arr", arr.toString())

        // loop
        for (item in arr) {
            Log.d("item", item)
        }

        val count = arr.count()
        for ( i in 0..count - 1 ) {
            Log.d("Val", arr.get(i))
        }

        val set = mutableSetOf<String>()
        set.add("Ali")
        set.add("Ahmet")
        set.add("Ahmet")
        set.add("Ahmet")
        set.add("Zehra")
        set.add("Ayşe")
        set.add("Ayşe")
        set.add("Mehmet")
        Log.d("Set", set.toString())

        // HashMap
        val map = mutableMapOf<String, Any>()
        map.put("name", "Kemal")
        map.put("surname", "Bilirim")
        map.put("surname", "Bil")
        map.put("email", "kemal@mail.com")
        map.put("status", true)
        map.put("age", 35)

        val mapName = map.get("name")
        val mapAge = map.get("age")
        if (mapAge != null && mapAge is Int) {
            val cAge = mapAge.toString().toInt()
            val sum = cAge + 10
            Log.d("CAge", sum.toString())
        }

        val keys = map.keys
        for (key in keys) {
            Log.d("Key", key)
            Log.d("Val", map.get(key).toString())
        }

        Log.d("Name", mapName.toString())
        Log.d("Map", map.toString())

        for ( (k, v) in map ) {
            Log.d(k, v.toString())
        }

        // Enum Map
        val enumMap = mutableMapOf<AppEnum, Any>()
        enumMap.put(AppEnum.NAME, "Selin")
        enumMap.put(AppEnum.SURNAME, "Bilmem")
        enumMap.put(AppEnum.AGE, 25)

        val itemEnum = enumMap.get(AppEnum.NAME)
        Log.d("TAG", itemEnum.toString())

        val setting = Settings()
        setting.func1()
        setting.dbConnect()
        val sum = setting.call(50,60)

        val sumAction = setting.action(50, "Android App")
        Log.d("sumAction", sumAction.toString())

        val pro1 = PropsProduct()
        pro1.title = "TV"
        pro1.detail = "TV Detail"
        pro1.price = 30000

        val pro2 = PropsProduct()
        pro2.title = "iPhone"
        pro2.detail = "iPhone Detail"
        pro2.price = 60000

        val arrProps = mutableSetOf<PropsProduct>()
        arrProps.add(pro1)
        arrProps.add(pro2)

        for ( item in arrProps ) {
            Log.d("title", item.toString())
        }

        val p1 = Product("Samsung", "Samsung Detail", 40000)
        val p2 = Product("Pro-1", "Pro-1 Detail", 20000)
        val p3 = Product("Pro-2", "Pro-2 Detail", 10000)

        val dataSet = mutableSetOf<Product>()
        dataSet.add(p1)
        dataSet.add(p1)
        dataSet.add(p1)
        dataSet.add(p2)
        dataSet.add(p2)
        dataSet.add(p2)
        dataSet.add(p2)
        dataSet.add(p3)

        for (item in dataSet) {
            Log.d("setItem", item.toString())
        }




    }
}