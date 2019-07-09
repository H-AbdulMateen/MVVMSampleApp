package com.isofttechpro.mvvmsampleapp.data.repositories

import com.isofttechpro.mvvmsampleapp.data.db.AppDatabase
import com.isofttechpro.mvvmsampleapp.data.db.entities.User
import com.isofttechpro.mvvmsampleapp.data.network.MyApi
import com.isofttechpro.mvvmsampleapp.data.network.SafeApiRequest
import com.isofttechpro.mvvmsampleapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(private val api: MyApi,
                     private val db: AppDatabase) : SafeApiRequest(){

   suspend fun userLogin(email : String, password : String) : AuthResponse {

       return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}