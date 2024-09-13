package com.example.kanbanbz.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "commentTable",
    foreignKeys = [ForeignKey(
        entity = TaskEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "taskId")
    val taskId: Int,
    @ColumnInfo(name = "text")
    val text: String
)
