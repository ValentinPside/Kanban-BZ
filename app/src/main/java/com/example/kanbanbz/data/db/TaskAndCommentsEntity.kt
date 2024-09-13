package com.example.kanbanbz.data.db

import androidx.room.Embedded
import androidx.room.Relation
import okhttp3.internal.concurrent.Task

class TaskAndCommentsEntity {
    @Embedded
    var task: Task? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    var comments: List<CommentEntity> = ArrayList()
}