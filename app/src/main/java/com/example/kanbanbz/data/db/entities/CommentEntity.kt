package com.example.kanbanbz.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "commentTable",
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "taskId")
    val taskId: Int,
    @ColumnInfo(name = "text")
    val text: String
)
