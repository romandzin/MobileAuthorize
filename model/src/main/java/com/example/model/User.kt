package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "email") var email: String?
)
