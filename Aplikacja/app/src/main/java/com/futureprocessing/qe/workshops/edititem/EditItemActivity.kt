package com.futureprocessing.qe.workshops.edititem


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import com.f2prateek.dart.Dart
import com.f2prateek.dart.InjectExtra
import com.futureprocessing.qe.workshops.MyApplication
import com.futureprocessing.qe.workshops.R
import com.futureprocessing.qe.workshops.common.AppNavigator
import com.futureprocessing.qe.workshops.databinding.ActivityEditItemBinding
import com.futureprocessing.qe.workshops.messages.Messenger


class EditItemActivity : AppCompatActivity() {

    @InjectExtra @JvmField var itemId: Int? = null

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { EditItemViewModel(itemId, myApplication.db, myApplication.session, AppNavigator(this), Messenger()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dart.inject(this)

        (DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_edit_item) as ActivityEditItemBinding).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}