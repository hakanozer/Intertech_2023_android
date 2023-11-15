package com.works.project.models

data class ProductsModel (
    val products: List<ProductModel>,
    val total: Long,
    val skip: Long,
    val limit: Long
)

data class ProductModel (
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
