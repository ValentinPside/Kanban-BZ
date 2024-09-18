package com.example.kanbanbz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kanbanbz.data.db.entities.CommentEntity
import com.example.kanbanbz.data.db.entities.TaskEntity

@Database(version = 1, entities = [TaskEntity::class, CommentEntity::class], exportSchema = false)
abstract class MainDb : RoomDatabase() {
    abstract fun dao(): Dao
}