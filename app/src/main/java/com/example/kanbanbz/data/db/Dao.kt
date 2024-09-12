package com.example.kanbanbz.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertTaskTable(taskEntity: TaskEntity)

    @Query("SELECT * FROM taskTable")
    fun getAllFromTaskTable(): Flow<List<TaskEntity>>

    @Query("DELETE FROM taskTable")
    fun removeAll()
}