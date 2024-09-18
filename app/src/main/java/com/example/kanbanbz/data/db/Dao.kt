package com.example.kanbanbz.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.kanbanbz.data.db.entities.CommentEntity
import com.example.kanbanbz.data.db.entities.TaskAndCommentsEntity
import com.example.kanbanbz.data.db.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertTaskTable(taskEntity: TaskEntity)

    @Upsert
    suspend fun upsertCommentsTable(commentEntity: CommentEntity)

    @Query("SELECT * FROM taskTable WHERE id = :taskId")
    fun getTasksById(taskId: Int): TaskAndCommentsEntity

    @Query("SELECT * FROM taskTable WHERE state = 1")
    fun getStartTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskTable WHERE state = 2")
    fun getInProgressTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskTable WHERE state = 3")
    fun getDoneTasks(): Flow<List<TaskEntity>>

    @Query("DELETE FROM taskTable")
    fun removeAll()
}