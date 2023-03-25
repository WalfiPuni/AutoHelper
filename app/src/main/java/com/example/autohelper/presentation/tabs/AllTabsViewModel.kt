package com.example.autohelper.presentation.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.autohelper.data.db.MainDatabase
import com.example.autohelper.data.users.room.entities.UserEntity
import kotlinx.coroutines.launch

class AllTabsViewModel(database: MainDatabase): ViewModel() {

    var currentUser = MutableLiveData<UserEntity>()
    val dao = database.getUserDao()

    fun setCurrentUser(login: String){
        viewModelScope.launch {
            currentUser.value = dao.getByLogin(login)
        }
    }




    class AllTabsViewModelFactory(val database: MainDatabase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(AllTabsViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AllTabsViewModel(database) as T
            }
            throw IllegalArgumentException("Unknow ViewModelClass")
        }
    }

}