package com.example.autohelper.presentation.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.autohelper.R
import com.example.autohelper.data.users.SignUpData
import com.example.autohelper.databinding.FragmentSignUpBinding
import com.example.autohelper.presentation.MainApp


class SignUpFragment: Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by activityViewModels {
        SignUpViewModel.SignUpViewModelFactory((context?.applicationContext as MainApp).database)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        viewModel.singUpSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(activity, R.string.success_signUp, Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
                viewModel.singUpIsSuccess()
            }
        }

        viewModel.singUpNotSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(activity, R.string.login_busy, Toast.LENGTH_LONG).show()
                viewModel.singUpIsNotSuccess()
            }
        }

        binding.btnRegistration.setOnClickListener {
            onBtnRegistrationPressed()
        }
    }

    private fun onBtnRegistrationPressed(){
        val signUpData = SignUpData(
            login = binding.edLoginUp.text.toString(),
            name = binding.edName.text.toString(),
            password = binding.edPasswordUp.text.toString(),
            repeatpassword = binding.edRepeatPassword.text.toString()
        )
        if(signUpData.validate() == null){
                viewModel.createNewUser(signUpData)
//                Toast.makeText(activity, R.string.success_signUp, Toast.LENGTH_LONG).show()
//                findNavController().popBackStack()
        }else{
            Toast.makeText(activity, signUpData.validate(), Toast.LENGTH_LONG).show()
        }
    }


}