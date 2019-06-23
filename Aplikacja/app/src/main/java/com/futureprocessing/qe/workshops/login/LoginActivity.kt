package com.futureprocessing.qe.workshops.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.futureprocessing.qe.workshops.MyApplication

import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { LoginViewModel(myApplication.db, myApplication.session, AppNavigator(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}