package com.example.test.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.Dao.UserDao
import com.example.test.table.User


/**
 * Created by Tanvir3488 on 2/14/2023.
 */

@Database(
    entities = [User::class], version = 1

)
abstract class UserDB: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var instanceCountViolation: UserDB? = null
        fun getInstance(context: Context): UserDB {

            var instance: UserDB? = instanceCountViolation
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext, UserDB::class.java, "User_DB"
                ).build()
            }
            return instance
        }

    }

}