package com.example.test.ui.AllUser

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
class AllUserViewModel @Inject constructor(val context: Context, val userDao: UserDao): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getAllUser():LiveData<List<User>>{

        val alluser = MutableLiveData<List<User>>().apply {
            viewModelScope.launch {
                value = userDao.getAllUser()
            }
        }
        val all_user: LiveData<List<User>> = alluser

        return all_user
    }
}