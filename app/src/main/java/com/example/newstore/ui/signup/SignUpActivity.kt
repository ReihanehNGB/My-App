package com.example.newstore.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.newstore.R
import com.example.newstore.databinding.ActivitySignUpBinding
import com.example.newstore.databinding.ProfileFragmentBinding
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.UserM
import com.example.newstore.ui.MainActivity
import com.example.newstore.ui.login.LoginActivity
import com.example.newstore.utils.MUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val signUpVM: SignUpVM by viewModels()
    var error: String = ""
    var phone: String = "09361318220"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding(R.layout.activity_sign_up)
        observe()
    }


    fun signUp(view: View?) {
//        phone = binding.etPhone.text.toString()
        signUpVM.createAccount(
            binding.etName.text.toString(),
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnNfxn7JD5QaLWQTgP409ZN19ii6Xz4IJIJg&usqp=CAU",
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString(),
            binding.etRole.text.toString()
        )



        MUtils.saveStateLogin(this,"Is Login",true)
    }

    fun login(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkLoginUser(UserModel: UserM) {
        val stateLogin = MUtils.checkLogin(this, "Is Login")
        if (stateLogin) {
            MUtils.saveToShared(this, "SingleU", UserModel.id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun skeep(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observe() {
        signUpVM.profLiveDataUser.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    checkLoginUser(it.data)
                    Log.i("TAG", "observe:${it.data}" )
                }
                is NetworkResult.Failure -> {
                    error = it.errorMessage
                }
                else -> {}
            }
        }
    }//close observe

    private fun initBinding(layout: Int) {
        binding = DataBindingUtil.setContentView(this, layout)
        binding.presenter = this
    }//close init
}