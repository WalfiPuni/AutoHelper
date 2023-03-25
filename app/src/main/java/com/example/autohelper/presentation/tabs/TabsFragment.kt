package com.example.autohelper.presentation.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.autohelper.R
import com.example.autohelper.databinding.FragmentTabsBinding
import com.example.autohelper.presentation.MainApp


class TabsFragment: Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding
    private val args: TabsFragmentArgs by navArgs()

    private val viewModel: AllTabsViewModel by activityViewModels{
        AllTabsViewModel.AllTabsViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        viewModel.setCurrentUser(args.userLogin)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

    }
}