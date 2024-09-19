package com.example.kanbanbz.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
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

    @Transaction
    @Query("SELECT * FROM taskTable WHERE id = :taskId")
    suspend  fun getTasksById(taskId: Int): TaskAndCommentsEntity

    @Query("SELECT * FROM taskTable WHERE state = 1")
    suspend fun getStartTasks(): List<TaskEntity>

    @Query("SELECT * FROM taskTable WHERE state = 2")
    suspend fun getInProgressTasks(): List<TaskEntity>

    @Query("SELECT * FROM taskTable WHERE state = 3")
    suspend fun getDoneTasks(): List<TaskEntity>

    @Query("UPDATE taskTable SET state=:status WHERE id = :id")
    suspend fun updateStatus(id: Int, status: Int)

    @Query("DELETE FROM taskTable")
    fun removeAll()
}