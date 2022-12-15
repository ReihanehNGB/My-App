package com.example.newstore.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newstore.R
import com.example.newstore.databinding.ActivityLoginBinding
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.UserM
import com.example.newstore.ui.MainActivity
import com.example.newstore.ui.signup.SignUpActivity
import com.example.newstore.utils.MUtils
import com.example.newstore.utils.vibrate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val loginVM: LoginVM by viewModels()
    var error: String = ""
    var userList: MutableList<UserM> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_login)

        checkLoginUser()
        loginVM.listUser()
        observe()


    }


    fun observe(){
        loginVM.loginLiveData.observe(this) {

            when (it) {
                is NetworkResult.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    userList = it.data
//                    binding.progressBar.visibility = View.GONE
                }

                is NetworkResult.Failure -> {
                    error = it.errorMessage
//                    binding.progressBar.visibility = View.GONE
//                    binding.error.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    private fun checkLoginUser(){
        val stateLogin = MUtils.checkLogin(this,"Is Login")
        if (stateLogin){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun checkUserPass(view: View) {
        userList.forEach {
            if (it.name == (binding.etEmail.text.toString()) && it.password == (binding.etPassword.text.toString())) {
                MUtils.saveStateLogin(this,"Is Login",true)
                MUtils.saveToShared(this, "SingleU", it.id)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }//close if
            else {
                vibrate(500)
                binding.tvError.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.etEmail.setText("")
                    binding.etPassword.setText("")
                    binding.tvError.visibility = View.GONE
                }, 3000)
            }
        }

    }


    fun signUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun skeep(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this

    }//close init




}