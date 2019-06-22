package com.futureprocessing.qe.workshops.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val viewModel by lazy { RegisterViewModel(AppNavigator(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}