package com.example.autohelper.presentation.tabs.addedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.autohelper.data.db.MainDatabase
import com.example.autohelper.data.notes.room.entities.NoteEntity
import kotlinx.coroutines.launch

class AddEditViewModel(database: MainDatabase) : ViewModel() {


    val dao = database.getNoteDao()

    fun insertNote(note: NoteEntity){
        viewModelScope.launch {
            dao.insertNote(note)
        }
    }
    fun updateNote(note: NoteEntity){
        viewModelScope.launch {
            dao.updateNote(note)
        }
    }


    class AddEditViewModelFactory(val database: MainDatabase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AddEditViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AddEditViewModel(database) as T
            }
            throw IllegalArgumentException("Unknow ViewModelClass")
        }
    }



}