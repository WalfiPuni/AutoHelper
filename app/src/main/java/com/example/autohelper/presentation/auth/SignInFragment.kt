package com.example.autohelper.presentation.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.autohelper.R
import com.example.autohelper.databinding.FragmentSignInBinding
import com.example.autohelper.presentation.MainApp


class SignInFragment: Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding


    private val viewModel: SignInViewModel by activityViewModels {
        SignInViewModel.SignInViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)


        viewModel.singInSuccess.observe(viewLifecycleOwner){
            if(it){
                val direction = SignInFragmentDirections.actionSignInFragmentToTabsFragment(binding.edLogin.text.toString())
                findNavController().navigate(direction)
                viewModel.singInIsSuccess()
            }
        }
        viewModel.singInNotSuccess.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(activity, R.string.success_signIn, Toast.LENGTH_LONG).show()
                viewModel.singInIsNotSuccess()
            }
        }

        binding.btnSignIn.setOnClickListener {
            if(binding.edLogin.text.toString().isBlank() || binding.edPassword.text.toString().isBlank()){
                Toast.makeText(activity, R.string.enter_signIn_data, Toast.LENGTH_LONG).show()
            }else{
                onBtnSignIn()
            }

        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }


    private fun onBtnSignIn() {
        viewModel.signIn(
            binding.edLogin.text.toString(),
            binding.edPassword.text.toString()
        )
    }

}