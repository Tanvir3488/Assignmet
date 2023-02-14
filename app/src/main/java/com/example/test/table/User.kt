package com.example.test.table

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Created by Tanvir3488 on 2/14/2023.
 */

@Entity(tableName = "User")
@Parcelize
data class User( @PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "id")
                 val id: Int,
                 @ColumnInfo(name = "first_name")
                 val first_name: String,
                 @ColumnInfo(name = "last_name")
                 val last_name: String,
                 @ColumnInfo(name = "phone_number")
                 val phone_number: String,
                 @ColumnInfo(name = "email")
                 val email: String
):Parcelable