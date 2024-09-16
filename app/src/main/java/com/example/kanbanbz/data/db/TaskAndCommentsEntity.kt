package com.example.kanbanbz.data.db

import androidx.room.Embedded
import androidx.room.Relation

class TaskAndCommentsEntity {
    @Embedded
    var task: TaskEntity? = null

    @Relation(
        parentColumn = "id",
        entityColumn = "taskId",
        entity = CommentEntity::class
    )
    var comments: List<CommentEntity>? = null
}