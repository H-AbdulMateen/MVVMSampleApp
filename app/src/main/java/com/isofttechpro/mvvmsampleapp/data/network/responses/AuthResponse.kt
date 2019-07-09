package com.isofttechpro.mvvmsampleapp.data.network.responses

import com.isofttechpro.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)