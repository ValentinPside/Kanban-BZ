package com.example.kanbanbz.presentation.viewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kanbanbz.R
import com.example.kanbanbz.databinding.FragmentNewTaskBinding
import com.example.kanbanbz.domain.DbRepository
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.utils.Factory
import com.example.kanbanbz.utils.asTaskList
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
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class TaskState(
    val taskName: String? = null,
    val comments: String? = null,
    val error: Int? = null
)