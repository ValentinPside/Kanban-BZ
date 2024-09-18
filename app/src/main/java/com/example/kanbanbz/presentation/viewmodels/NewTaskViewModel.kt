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

class NewTaskViewModel @Inject constructor(
    private val dbRepository: DbRepository
) : ViewModel() {

    private val state = MutableStateFlow(NewTaskState())
    fun observeUi() = state.asStateFlow()
    fun addNewTask(name: String) {
        viewModelScope.launch {
            try {
                dbRepository.addNewTask(name)
                state.update { it.copy(success = R.string.success_message, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class NewTaskState(
    val success: Int? = null,
    val error: Int? = null
)