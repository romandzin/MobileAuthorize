package com.example.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE firstName IN (:firstName) LIMIT 1")
    suspend fun loadUserByFirstName(firstName: String): User?

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Delete
    suspend fun deleteAll(list: List<User>)

}