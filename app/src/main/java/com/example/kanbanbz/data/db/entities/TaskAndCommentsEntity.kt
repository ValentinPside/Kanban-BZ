package com.example.kanbanbz.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.kanbanbz.data.db.entities.CommentEntity
import com.example.kanbanbz.data.db.entities.TaskEntity

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