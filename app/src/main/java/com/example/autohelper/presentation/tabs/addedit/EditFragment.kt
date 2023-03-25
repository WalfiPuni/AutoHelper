package com.example.autohelper.presentation.tabs.addedit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.autohelper.R
import com.example.autohelper.data.notes.room.entities.NoteEntity
import com.example.autohelper.databinding.FragmentEditBinding
import com.example.autohelper.presentation.MainApp

class EditFragment: Fragment(R.layout.fragment_edit) {

    private lateinit var binding: FragmentEditBinding
    private var note: NoteEntity? = null
    private val viewModel: AddEditViewModel by activityViewModels {
        AddEditViewModel.AddEditViewModelFactory((context?.applicationContext as MainApp).database)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditBinding.bind(view)

        note = requireArguments().getSerializable(KEY_NOTE) as NoteEntity
        binding.edNameDetail2.setText(note?.nameDetail)
        binding.edMileage2.setText(note?.mileage.toString())
        binding.edDesc2.setText(note?.description)

        binding.btnSave2.setOnClickListener {
            update()
        }

    }


    private fun update(){
        if(validate()) {
            val upNote = note?.copy(
                nameDetail = binding.edNameDetail2.text.toString(),
                mileage = binding.edMileage2.text.toString().toInt(),
                description = binding.edDesc2.text.toString()
            )
            viewModel.updateNote(upNote!!)
            findNavController().popBackStack()
            toast(R.string.success_update)
        }else{
            toast(R.string.validate_save)
        }
    }

    companion object{
        const val KEY_NOTE = "key_note"
    }

    private fun validate(): Boolean{
        if(binding.edNameDetail2.text.toString().isBlank() ||
            binding.edDesc2.text.toString().isBlank()||
            binding.edMileage2.text.toString().isBlank()){
            return false
        }
        return true
    }

    private fun toast(res: Int){
        Toast.makeText(activity, res, Toast.LENGTH_LONG).show()
    }
}