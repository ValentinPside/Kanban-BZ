package com.example.kanbanbz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [TaskEntity::class], exportSchema = false)
abstract class MainDb : RoomDatabase() {
    abstract fun dao(): Dao
}