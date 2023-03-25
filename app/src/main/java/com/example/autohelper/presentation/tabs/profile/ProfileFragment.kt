package com.example.autohelper.presentation.tabs.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.autohelper.R
import com.example.autohelper.databinding.FragmentProfileBinding
import com.example.autohelper.presentation.MainApp
import com.example.autohelper.presentation.auth.SignInViewModel
import com.example.autohelper.presentation.tabs.AllTabsViewModel
import com.example.autohelper.presentation.tabs.TabsFragment


class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: AllTabsViewModel by activityViewModels{
        AllTabsViewModel.AllTabsViewModelFactory((context?.applicationContext as MainApp).database)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        viewModel.currentUser.observe(viewLifecycleOwner){
            binding.tvLogin.text = it.login
            binding.tvName.text = it.name
        }

        binding.btnLogout.setOnClickListener {
            findTopNavController().navigate(R.id.signInFragment, null, navOptions {
                popUpTo(R.id.tabsFragment){
                    inclusive = true
                }
            })
        }

    }
}


fun Fragment.findTopNavController(): NavController {
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}