package com.sungil.runningproejct_mvvm.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.BuildConfig
import com.sungil.runningproejct_mvvm.R
import com.sungil.runningproejct_mvvm.databinding.ActivityLoginBinding
import com.sungil.runningproejct_mvvm.login.factory.LoginFactory
import com.sungil.runningproejct_mvvm.login.viewModel.LoginViewModel
import com.sungil.runningproejct_mvvm.`object`.LoginData
import com.sungil.runningproejct_mvvm.repository.loginImpl.LoginRepoImpl
import timber.log.Timber

/**
 * 로그인 화묜
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityLoginBinding

    private var _viewModel : LoginViewModel ?= null
    private val viewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        addListener()
    }
    private fun initViewModel(){
        val factory = LoginFactory(LoginRepoImpl(this))
        _viewModel = ViewModelProvider(this , factory)[LoginViewModel :: class.java]
    }

    private fun addListener(){
        //개발자 용
        binding.icIcon.setOnClickListener {
            if(!BuildConfig.DEBUG){
                binding.editId.setText("sonny132@naver.com")
                binding.editPassword.setText("12345")
            }
        }

        //로그인 LiveData
        //Resour
        viewModel.loginLiveData.observe(this , Observer {loginStatus ->
            when(loginStatus) {
                is LoginViewModel.LoginStatus.LoginLoading->{
                    Timber.d("Loading for Login")
                    Toast.makeText(this , getString(R.string.msg_login_loading), Toast.LENGTH_SHORT ).show()
                }

                is LoginViewModel.LoginStatus.LoginSuccess ->{
                    Timber.d("Success for Login")
                    Toast.makeText(this , getString(R.string.msg_success_login), Toast.LENGTH_SHORT ).show()
                }
                is LoginViewModel.LoginStatus.LoginError ->{
                    Timber.e("ERROR to Login")
                }
            }
        })

        //로그인시 회원정보가 있는지 판별
        binding.btnLogin.setOnClickListener {
            val id = binding.editId.text.toString().replace(" ","").replace(".","")
            val password = binding.editPassword.text.toString().replace(" ", "").replace(".","")
            if(id == "" || password ==""){
                Timber.d("id or password is Null")
                Toast.makeText(this , getString(R.string.msg_pls_input_userinfo) , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data = LoginData(id , password)
            viewModel.requestLogin(data)
        }

        //회원가입 activity로 가기 위한
        binding.layoutBottom.setOnClickListener {
            Timber.d("The User Select Sign Up")
            val intent = Intent(this , SignUpActivity :: class.java)
            startActivity(intent)
        }
    }
}