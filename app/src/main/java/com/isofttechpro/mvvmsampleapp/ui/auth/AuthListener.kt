package com.isofttechpro.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.isofttechpro.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}