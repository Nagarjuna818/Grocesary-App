package com.example.grocessaryapp.models

data class LoginResponse(
    val token: String?=null,
    val user: User?=null
)

data class RegisterResponse(
    val data: User,
    val error: Boolean,
    val message: String
)

data class User(
    val __v: Int? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val mobile: String? = null,
    val password: String? = null,
)