package com.isofttechpro.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.isofttechpro.mvvmsampleapp.R
import com.isofttechpro.mvvmsampleapp.data.db.AppDatabase
import com.isofttechpro.mvvmsampleapp.data.db.entities.User
import com.isofttechpro.mvvmsampleapp.data.network.MyApi
import com.isofttechpro.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.isofttechpro.mvvmsampleapp.data.repositories.UserRepository
import com.isofttechpro.mvvmsampleapp.databinding.ActivityLoginBinding
import com.isofttechpro.mvvmsampleapp.ui.home.HomeActivity
import com.isofttechpro.mvvmsampleapp.utils.hide
import com.isofttechpro.mvvmsampleapp.utils.show
import com.isofttechpro.mvvmsampleapp.utils.snackbar
import com.isofttechpro.mvvmsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.progress_bar

class LoginActivity : AppCompatActivity() , AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel :: class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null){
                Intent(this, HomeActivity :: class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }
    override fun onStarted() {
//        toast("Login started..")
//        progress_bar.visibility = View.VISIBLE
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        root_layout.snackbar("${user.name} is Logged In")
//        toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
