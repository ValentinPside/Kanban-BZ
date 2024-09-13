package com.example.kanbanbz.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertTaskTable(taskEntity: TaskEntity)

    @Upsert
    suspend fun upsertCommentsTable(commentEntity: CommentEntity)

    @Query("SELECT * FROM taskTable")
    fun getAllTasks(): Flow<List<TaskAndCommentsEntity>>

    @Query("DELETE FROM taskTable")
    fun removeAll()
}