package com.example.kanbanbz.domain

import com.example.kanbanbz.data.db.entities.TaskEntity
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.domain.models.TaskAndComments
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getTasksById(id: Int): TaskAndComments?

    fun getStartTasks(): Flow<List<TaskEntity>>

    fun getInProgressTasks(): Flow<List<TaskEntity>>

    fun getDoneTasks(): Flow<List<TaskEntity>>

    suspend fun addNewTask(name: String)

}