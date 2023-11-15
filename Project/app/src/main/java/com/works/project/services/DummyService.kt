package com.works.project.services

import com.works.project.models.JWTModel
import com.works.project.models.ProductsModel
import com.works.project.models.SendUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DummyService {

    @POST("auth/login")
    fun login(@Body sendUser: SendUser) : Call<JWTModel>

    @GET("products")
    fun allProduct() : Call<ProductsModel>


}