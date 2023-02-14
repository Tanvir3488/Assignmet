package com.example.test.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.test.table.User


/**
 * Created by Tanvir3488 on 2/14/2023.
 */

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user:User):Long

    @Update
    suspend fun updateuser(user:User):Int

    @Query("select * from user where phone_number=:phone")
    suspend fun getUserbyPhone(phone:String):User

    @Query("select * from user")
    suspend fun getAllUser():List<User>
}