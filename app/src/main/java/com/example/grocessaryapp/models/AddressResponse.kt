package com.example.grocessaryapp.models

data class AddressResponse(
    val count: Int,
    val `data`: ArrayList<Address>,
    val error: Boolean
)

data class Address(
    val __v: Int,
    val _id: String,
    val city: String,
    val houseNo: String,
    val location: String,
    val mobile: String,
    val name: String,
    val pincode: Int,
    val streetName: String,
    val type: String,
    val userId: String
)