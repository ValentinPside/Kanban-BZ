package com.example.kanbanbz.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "taskTable"
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "state")
    val state: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date")
    val date: String
)
