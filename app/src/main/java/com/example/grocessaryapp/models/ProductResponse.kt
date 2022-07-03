package com.example.grocessaryapp.models

import java.io.Serializable

data class ProductResponse(
    val count: Int?= null,
    val `data`: ArrayList<Product>?= null,
    val error: Boolean?= null
)

data class Product(
    val __v: Int? = null,
    val _id: String? = null,
    val catId: Int? = null,
    val created: String? = null,
    val description: String? = null,
    val image: String? = null,
    val mrp: Double? = null,
    val position: Int? = null,
    val price: Double? = null,
    val productName: String? = null,
    var quantity: Int? = null,
    val status: Boolean? = null,
    val subId: Int? = null,
    val unit: String? = null
): Serializable {
    companion object {
        const val KEY_PRODUCT = "product"
    }
}