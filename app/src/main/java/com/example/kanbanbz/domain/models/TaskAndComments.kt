package com.example.kanbanbz.domain.models

data class TaskAndComments(
    val id: Int,
    val name: String,
    val commentList: List<Comment>
)