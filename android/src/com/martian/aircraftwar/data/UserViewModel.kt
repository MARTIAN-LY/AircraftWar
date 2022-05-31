package com.martian.aircraftwar.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val allData: LiveData<List<User>>
    val userDao: UserDao

    init {
        userDao = UserDatabase.getInstance(application).userDao()
        allData = userDao.getAllUser()
    }

    /** 在新线程中添加一个用户 */
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
        }
    }


}