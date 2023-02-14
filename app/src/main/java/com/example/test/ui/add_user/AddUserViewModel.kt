package com.example.test.ui.add_user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.Dao.UserDao
import com.example.test.table.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(val context:Context,val userDao: UserDao) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Add User"
    }
    val text: LiveData<String> = _text


    fun getDuplicateUser(phone:String):LiveData<User>{

       val user = MutableLiveData<User>().apply {
         viewModelScope.launch {
           value = userDao.getUserbyPhone(phone)
         }
       }
        val dup_user: LiveData<User> = user

        return dup_user
    }

    fun addUser(user: User){

        viewModelScope.launch {
            userDao.addUser(user)
        }
    }

    fun updateuser(user: User){

        viewModelScope.launch {
            userDao.updateuser(user)
        }
    }
}