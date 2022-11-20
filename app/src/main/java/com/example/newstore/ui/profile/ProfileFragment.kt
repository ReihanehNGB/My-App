package com.example.newstore.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newstore.R
import com.example.newstore.databinding.ProfileFragmentBinding
import com.example.newstore.model.NetworkResult
import com.example.newstore.model.UserM
import com.example.newstore.ui.details.ProfileVM
import com.example.newstore.utils.MUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: ProfileFragmentBinding
    private val profileVM: ProfileVM by viewModels()
    var lUserModel: MutableList<UserM> = ArrayList()
    var error: String = ""
    var userID: String = ""
    lateinit var sharedPreferences: SharedPreferences

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


        profileVM.allUser()



        profileVM.profLiveDataU.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE

                }
                is NetworkResult.Success -> {
                    createProfile(it.data)


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
            if (it.id.toString() == vlu){
                binding.userModel = it
            }
        }



    }


}//close Class