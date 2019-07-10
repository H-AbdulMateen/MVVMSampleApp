package com.isofttechpro.mvvmsampleapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isofttechpro.mvvmsampleapp.R
import com.isofttechpro.mvvmsampleapp.utils.toast

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toast("Welcome to MVVM Architecture Sample")
    }
}
