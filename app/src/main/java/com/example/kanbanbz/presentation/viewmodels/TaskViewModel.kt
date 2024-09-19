package com.example.kanbanbz.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanbanbz.R
import com.example.kanbanbz.domain.DbRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val dbRepository: DbRepository,
    id: String
) : ViewModel() {

    private val state = MutableStateFlow(TaskState())
    fun observeUi() = state.asStateFlow()

    init {
        getTask(id.toInt())
    }

    private fun getTask(id: Int) {
        viewModelScope.launch {
            try {
                val taskAndComments = dbRepository.getTasksById(id)
                val name = taskAndComments?.name
                val commentsText = taskAndComments?.commentList.toString()
                state.update { it.copy(taskName = name, comments = commentsText, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.empty_comments_message) }
            }
        }
    }

    /*fun addComment(id: Int, commentText: String){
        viewModelScope.launch {
            try {
                dbRepository.addNewComment(id, commentText)
                state.update { it.copy(success = R.string.add_comment_message, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }*/
}

data class TaskState(
    val taskName: String? = null,
    val comments: String? = null,
    val success: Int? = null,
    val error: Int? = null
)