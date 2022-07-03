package com.example.grocessaryapp.models

data class CategoryResponse(
    val count:Int? = null,
    val data: ArrayList<Category>? = null,
    val error: Boolean? = null
)

data class Slider(
    val catImage: String
)

data class Category(
    val __v: Int? = null,
    val _id: String? = null,
    val catDescription: String? = null,
    val catId: Int? = null,
    val catImage: String,
    val catName: String? = null,
    val position: Int? = null,
    val slug: String? = null,
    val status: Boolean? = null
)