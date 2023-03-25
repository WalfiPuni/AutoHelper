package com.example.autohelper.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.autohelper.data.db.MainDatabase
import com.example.autohelper.data.users.SignInData
import kotlinx.coroutines.launch

class SignInViewModel(database: MainDatabase) : ViewModel() {

    val singInSuccess = MutableLiveData<Boolean>()
    val singInNotSuccess = MutableLiveData<Boolean>()
    val dao = database.getUserDao()

    fun signIn(login: String, password: String){
        viewModelScope.launch {
            val signInData = dao.findByLogin(login = login)
            if (signInData == null || signInData.password != password){
                singInNotSuccess.value = true
            } else{
                singInSuccess.value = true
            }
        }
    }

    class SignInViewModelFactory(val database: MainDatabase) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SignInViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SignInViewModel(database) as T
            }
            throw IllegalArgumentException("Unknow ViewModelClass")
        }
    }

    fun singInIsSuccess(){
        singInSuccess.value = false
    }

    fun singInIsNotSuccess(){
        singInNotSuccess.value = false
    }

}