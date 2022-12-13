package com.example.newstore.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newstore.R
import com.example.newstore.databinding.ProfileFragmentBinding
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.UserM
import com.example.newstore.ui.login.LoginActivity
import com.example.newstore.utils.MUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: ProfileFragmentBinding
    private val profileVM: ProfileVM by viewModels()
    var lUserModel: MutableList<UserM> = ArrayList()
    var error: String = ""
    var userID: String = "0"
    var userIMG: String = ""
    var userPASS: String = ""
    lateinit var sharedPreferences: SharedPreferences
    lateinit var userModel: UserM

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.presenter = this

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)



        observe()




        binding.llExit.setOnClickListener {
            binding.rl.visibility = View.GONE
            binding.btnSignIn.visibility = View.VISIBLE
        }
        binding.btnSignIn.setOnClickListener{
            MUtils.deleteUser(requireContext(), "Is Login", null)
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }


        profileVM.allUser()







        profileVM.profLiveDataU.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE

                }
                is NetworkResult.Success -> {
                    checkLoginUser(it.data)
                }
                is NetworkResult.Failure -> {
                    val error = it.errorMessage

                }
                else -> {}

            }//close when

        }//close observe








        return binding.root

    }


    private fun createProfile(lUserModel: MutableList<UserM>) {
        val vlu = MUtils.readToShared(requireContext(),"SingleU")
        lUserModel.forEach {
            if (it.id == vlu){
                binding.userModel = it
                userModel = it
                userID = it.id
                userIMG = it.avatar
                userPASS = it.password
            }
        }


    }




    private fun updateProfile() {
        val newName = binding.etName.text.toString()
        val newEmail = binding.etEmail.text.toString()
        if (newName!=""){
            userModel = UserM(
                userID,
                newName,
                userIMG,
                newEmail,
                userPASS
            )
            binding.userModel = userModel
            profileVM.updateUser(newName,newEmail,userPASS,userIMG)

        }else{
            binding.errorNullName.visibility = View.VISIBLE
            binding.newName.visibility = View.GONE
            binding.parentName.setOnClickListener {
                binding.errorNullName.visibility = View.GONE
                editName(view = null)
            }
        }


    }


    private fun checkLoginUser(lUserModel: MutableList<UserM>){
        val stateLogin = MUtils.checkLogin(requireContext(),"Is Login")
        if (stateLogin){
            createProfile(lUserModel)
        }else{
            binding.rl.visibility = View.GONE
            binding.btnSignIn.visibility = View.VISIBLE
        }

    }


    fun editName(view: View?){
            binding.newName.visibility = View.GONE
            binding.etName.visibility = View.VISIBLE
            binding.editIcon.visibility = View.GONE
            binding.editDone.visibility = View.VISIBLE

    }


    fun editEmail(view: View){
                binding.newEmail.visibility = View.GONE
                binding.etEmail.visibility = View.VISIBLE
                binding.editIconE.visibility = View.GONE
                binding.editDoneE.visibility = View.VISIBLE
    }

    fun editDoneName(view: View){
        binding.etName.visibility = View.GONE
        binding.newName.visibility = View.VISIBLE
        binding.editDone.visibility = View.GONE
        binding.editIcon.visibility = View.VISIBLE
        updateProfile()
        Toast.makeText(requireContext(), "Name Changed", Toast.LENGTH_LONG).show()
    }
    fun editDoneEmail(view: View){
        binding.etEmail.visibility = View.GONE
        binding.newEmail.visibility = View.VISIBLE
        binding.editDoneE.visibility = View.GONE
        binding.editIconE.visibility = View.VISIBLE
        updateProfile()
        Toast.makeText(requireContext(), "Name Changed", Toast.LENGTH_LONG).show()
    }


    private fun observe(){
        profileVM.profLiveDataUser.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                }
                is NetworkResult.Success -> {
                    binding.userModel = it.data
                    Log.i("TAG", "observe:${it.data} ")
                }
                is NetworkResult.Failure -> {
                    error = it.errorMessage
                }
                else -> {}
            }
        }
    }




}//close Class