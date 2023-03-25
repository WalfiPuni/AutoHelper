package com.example.autohelper.presentation.tabs.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autohelper.R
import com.example.autohelper.data.notes.room.entities.NoteEntity
import com.example.autohelper.data.users.room.entities.UserEntity
import com.example.autohelper.databinding.FragmentListBinding
import com.example.autohelper.presentation.MainApp
import com.example.autohelper.presentation.NoteAdapter
import com.example.autohelper.presentation.tabs.AllTabsViewModel
import com.example.autohelper.presentation.tabs.addedit.EditFragment
import com.example.autohelper.presentation.tabs.profile.findTopNavController

class ListFragment: Fragment(R.layout.fragment_list), NoteAdapter.Listener {

    private val viewModel: ListViewModel by activityViewModels {
        ListViewModel.ListViewModelFactory((context?.applicationContext as MainApp).database)
    }
    private val viewModelGetUser: AllTabsViewModel by activityViewModels{
        AllTabsViewModel.AllTabsViewModelFactory((context?.applicationContext as MainApp).database)
    }
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: NoteAdapter
    private var userId: Int = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        viewModelGetUser.currentUser.observe(viewLifecycleOwner){
            userId = it.id!!
        }

        initRcView()
        observer()
    }


    private fun observer(){
        viewModel.AllNotes.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun initRcView(){
        binding.rcView.layoutManager = LinearLayoutManager(activity)
        adapter = NoteAdapter(this)
        binding.rcView.adapter = adapter

        viewModel.getList(userId)
    }

    override fun deleteItem(id: Int) {
        viewModel.deleteNote(id)
    }

    override fun onClickItem(note: NoteEntity) {
        findTopNavController().navigate(R.id.editFragment, bundleOf(EditFragment.KEY_NOTE to note))
    }
}