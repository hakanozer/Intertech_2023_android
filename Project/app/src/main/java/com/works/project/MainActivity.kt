package com.works.project

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.works.project.configs.ApiClient
import com.works.project.configs.Util
import com.works.project.models.JWTModel
import com.works.project.models.SendUser
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var txtUsername: EditText
    lateinit var txtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var dummyService: DummyService

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val jwtSt = sharedPreferences.getString("jwt", "")
        if ( jwtSt != null && jwtSt != "") {
            val gson = Gson()
            val jwt = gson.fromJson(jwtSt,JWTModel::class.java)
            Util.user = jwt
            val intent = Intent(this@MainActivity, Product::class.java)
            startActivity(intent)
            finish()
        }

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        txtUsername = findViewById(R.id.txtUsername)
        txtPassword = findViewById(R.id.txtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Button Click Listener
        btnLogin.setOnClickListener(btnOnClikListner)

    }


    val btnOnClikListner = View.OnClickListener() {
        val username = txtUsername.text.toString()
        val password = txtPassword.text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Username Empty!", Toast.LENGTH_SHORT).show()
            txtUsername.requestFocus()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password Empty!", Toast.LENGTH_SHORT).show()
            txtPassword.requestFocus()
        } else {

            // Form Send
            val sendUser = SendUser(username, password)
            dummyService.login(sendUser).enqueue( object : Callback<JWTModel> {
                override fun onResponse(call: Call<JWTModel>, response: Response<JWTModel>) {
                    val statusCode = response.code()
                    if (statusCode == 200) {
                        val jwt = response.body()
                        if (jwt != null) {
                            val gson = Gson()
                            val stJwt = gson.toJson(jwt)
                            editor.putString("jwt", stJwt)
                            editor.putString("token", jwt.token)
                            editor.commit()

                            val intent = Intent(this@MainActivity, Product::class.java)
                            startActivity(intent)
                            finish()

                        }
                    }else {
                        Toast.makeText(this@MainActivity, "Username or Password Fail!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<JWTModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Server Error!", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }

    /*
    fun btnClickLogin(view: View) {
        Toast.makeText(this, "Btn click!", Toast.LENGTH_SHORT).show()
    }
    */

}