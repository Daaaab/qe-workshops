package com.futureprocessing.qe.workshops.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val viewModel by lazy { LoginViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}