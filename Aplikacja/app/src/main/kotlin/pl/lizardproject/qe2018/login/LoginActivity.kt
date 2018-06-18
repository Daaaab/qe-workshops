package pl.lizardproject.qe2018.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pl.lizardproject.qe2018.MyApplication
import pl.lizardproject.qe2018.R
import pl.lizardproject.qe2018.databinding.ActivityLoginBinding
import pl.lizardproject.qe2018.navigation.AppNavigator

class LoginActivity : AppCompatActivity() {

    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val viewModel by lazy { LoginViewModel(myApplication.databaseFacade, myApplication.userSession, AppNavigator(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login).viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}