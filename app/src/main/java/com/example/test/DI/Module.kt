package com.example.test.DI

import android.content.Context
import com.example.test.DB.UserDB
import com.example.test.Dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Tanvir3488 on 2/14/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
class  Module {



    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context): UserDao {
        return UserDB.getInstance(context).userDao
    }






}