package com.example.autohelper.presentation.tabs.list

import androidx.lifecycle.*
import com.example.autohelper.data.db.MainDatabase
import com.example.autohelper.data.notes.room.entities.NoteEntity
import kotlinx.coroutines.launch

class ListViewModel(database: MainDatabase) : ViewModel() {

    val daoNote = database.getNoteDao()
    lateinit var AllNotes: LiveData<List<NoteEntity>>

    fun getList(userId: Int){
        AllNotes = daoNote.getAllNotes(userId).asLiveData()
//        val AllNotes: LiveData<List<NoteEntity>> = daoNote.getAllNotes(userId).asLiveData()
    }

    fun deleteNote(idNote: Int){
        viewModelScope.launch {
            daoNote.deleteNote(idNote)
        }
    }


    class ListViewModelFactory(val database: MainDatabase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ListViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ListViewModel(database) as T
            }
            throw IllegalArgumentException("Unknow ViewModelClass")
        }
    }
}