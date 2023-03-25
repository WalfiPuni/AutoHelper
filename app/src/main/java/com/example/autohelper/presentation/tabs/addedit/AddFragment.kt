package com.example.autohelper.presentation.tabs.addedit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.autohelper.R
import com.example.autohelper.data.notes.room.entities.NoteEntity
import com.example.autohelper.databinding.FragmentAddBinding
import com.example.autohelper.presentation.MainApp
import com.example.autohelper.presentation.tabs.AllTabsViewModel

class AddFragment: Fragment(R.layout.fragment_add) {

    private lateinit var binding: FragmentAddBinding
    private val viewModel: AddEditViewModel by activityViewModels {
        AddEditViewModel.AddEditViewModelFactory((context?.applicationContext as MainApp).database)
    }
    private val viewModelGetUser: AllTabsViewModel by activityViewModels{
        AllTabsViewModel.AllTabsViewModelFactory((context?.applicationContext as MainApp).database)
    }
    private var userId = -1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)


        viewModelGetUser.currentUser.observe(viewLifecycleOwner){
            userId = it.id!!
        }

        binding.btnSave.setOnClickListener {
            if(validate()){
                save()
            }else{
                Toast.makeText(activity, R.string.validate_save, Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun validate(): Boolean{
        if(binding.edNameDetail.text.toString().isBlank() ||
            binding.edDesc.text.toString().isBlank()||
            binding.edMileage.text.toString().isBlank()){
            return false
        }
        return true
    }


    private fun save(){
        val note = NoteEntity(
            null,
            binding.edNameDetail.text.toString(),
            binding.edMileage.text.toString().toInt(),
            binding.edDesc.text.toString(),
            userId
        )
        viewModel.insertNote(note)


        binding.edNameDetail.text.clear()
        binding.edMileage.text.clear()
        binding.edDesc.text.clear()
        Toast.makeText(activity, R.string.success_save, Toast.LENGTH_LONG).show()
    }

}