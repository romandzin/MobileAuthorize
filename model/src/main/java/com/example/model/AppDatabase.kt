package com.example.model

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(entities = [User::class], version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2, DeleteOldColumn::class)
], exportSchema = true)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

@DeleteColumn(tableName = "User", columnName = "uid")
class DeleteOldColumn : AutoMigrationSpec
