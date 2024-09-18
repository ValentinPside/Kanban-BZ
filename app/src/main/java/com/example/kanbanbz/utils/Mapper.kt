package com.example.kanbanbz.utils

import com.example.kanbanbz.data.db.entities.CommentEntity
import com.example.kanbanbz.data.db.entities.TaskAndCommentsEntity
import com.example.kanbanbz.data.db.entities.TaskEntity
import com.example.kanbanbz.domain.models.Comment
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.domain.models.TaskAndComments

fun TaskEntity.asTask() = Task(
    id = this.id,
    name = this.name,
    date = this.date
)

fun List<TaskEntity>.asTaskList() = this.map { it.asTask() }

fun CommentEntity.asComment() = Comment(
    text = this.text
)

fun List<CommentEntity>.asCommentsList() = this.map { it.asComment() }

fun TaskAndCommentsEntity.asTaskAndComments() = this.comments?.let {
    this.task?.let { it1 ->
        TaskAndComments(
        id = this.task!!.id,
        name = it1.name,
        commentList = it.asCommentsList()
    )
    }
}