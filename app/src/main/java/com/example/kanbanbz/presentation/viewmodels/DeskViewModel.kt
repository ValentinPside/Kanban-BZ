package com.example.kanbanbz.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanbanbz.R
import com.example.kanbanbz.domain.DbRepository
import com.example.kanbanbz.domain.models.Task
import com.example.kanbanbz.utils.asTaskList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeskViewModel @Inject constructor(
private val dbRepository: DbRepository
) : ViewModel() {

    private val state = MutableStateFlow(DeskState())
    fun observeUi() = state.asStateFlow()

    init {
        getTaskList()
    }

    private fun getTaskList() {
        viewModelScope.launch {
            try {
                val listStart = mutableListOf<Task>()
                val listInProgress = mutableListOf<Task>()
                val listDone = mutableListOf<Task>()
                dbRepository.getStartTasks().collect { it ->
                    listStart.addAll(it.asTaskList())
                    state.update { it.copy(startList = listStart, error = null) }
                }
                dbRepository.getInProgressTasks().collect { it ->
                    listInProgress.addAll(it.asTaskList())
                    state.update { it.copy(inProgressList = listInProgress, error = null) }
                }
                dbRepository.getDoneTasks().collect { it ->
                    listDone.addAll(it.asTaskList())
                    state.update { it.copy(doneList = listDone, error = null) }
                }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class DeskState(
    val startList: List<Task>? = null,
    val inProgressList: List<Task>? = null,
    val doneList: List<Task>? = null,
    val error: Int? = null
)