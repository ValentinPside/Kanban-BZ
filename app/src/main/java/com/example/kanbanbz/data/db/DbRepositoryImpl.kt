package com.example.kanbanbz.data.db

import android.annotation.SuppressLint
import com.example.kanbanbz.data.db.entities.CommentEntity
import com.example.kanbanbz.data.db.entities.TaskEntity
import com.example.kanbanbz.domain.DbRepository
import com.example.kanbanbz.domain.models.Comment
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.domain.models.TaskAndComments
import com.example.kanbanbz.utils.asTaskAndComments
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val db: MainDb) : DbRepository {

    override suspend fun getTasksById(id: Int): TaskAndComments? {
        return db.dao().getTasksById(id).asTaskAndComments()
    }

    override fun getStartTasks(): Flow<List<TaskEntity>>{
        return db.dao().getStartTasks()
    }

    override fun getInProgressTasks(): Flow<List<TaskEntity>>{
        return db.dao().getInProgressTasks()
    }

    override fun getDoneTasks(): Flow<List<TaskEntity>>{
        return db.dao().getDoneTasks()
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun addNewTask(name: String){
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val task = TaskEntity(
            state = 1,
            name = name,
            date = currentDate
        )
        db.dao().upsertTaskTable(task)
    }

    override suspend fun addNewComment(taskId: Int, text: String) {
        val commentEntity = CommentEntity(0, taskId, text)
        db.dao().upsertCommentsTable(commentEntity)
    }

}