package com.baharudin.latianroom.data

import androidx.lifecycle.LiveData

class UserRepository(private var userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.createUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateData(user)
    }
}