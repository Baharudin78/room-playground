package com.baharudin.latianroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createUser(user: User)
    @Query("SELECT * FROM user_table ORDER BY id")
     fun readAllData() : LiveData<List<User>>
     @Update
     suspend fun updateData(user: User)

}