package com.example.autohelper.presentation.auth

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.autohelper.R
import com.example.autohelper.data.db.MainDatabase
import com.example.autohelper.data.users.SignUpData
import com.example.autohelper.data.users.room.entities.UserEntity
import kotlinx.coroutines.launch

class SignUpViewModel(database: MainDatabase) : ViewModel() {

    val singUpSuccess = MutableLiveData<Boolean>()
    val singUpNotSuccess = MutableLiveData<Boolean>()
    val dao = database.getUserDao()

    fun createNewUser(signUpData: SignUpData){
        val newUser = UserEntity.fromSignUpData(signUpData)
        viewModelScope.launch {
            try{
                dao.createUser(newUser)
                singUpSuccess.value = true
            }catch (e: Exception){
                singUpNotSuccess.value = true
            }
        }
    }

    fun singUpIsSuccess(){
        singUpSuccess.value = false
    }

    fun singUpIsNotSuccess(){
        singUpNotSuccess.value = false
    }


    class SignUpViewModelFactory(val database: MainDatabase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SignUpViewModel(database) as T
            }
            throw IllegalArgumentException("Unknow ViewModelClass")
        }
    }

}