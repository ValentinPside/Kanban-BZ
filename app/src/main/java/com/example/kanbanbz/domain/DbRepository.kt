package com.example.kanbanbz.domain

import com.example.kanbanbz.data.db.entities.TaskEntity
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.domain.models.TaskAndComments
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    suspend fun getTasksById(id: Int): TaskAndComments?

    suspend fun getStartTasks(): List<TaskEntity>

    suspend fun getInProgressTasks(): List<TaskEntity>

    suspend fun getDoneTasks(): List<TaskEntity>

    suspend fun addNewTask(name: String)

    suspend fun addNewComment(taskId: Int, text: String)

    suspend fun changeStatus(id: Int, statusId: Int)

}