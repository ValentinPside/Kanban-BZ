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

class NewStatusViewModel @Inject constructor(
    private val dbRepository: DbRepository,
    id: String
) : ViewModel() {

    private val state = MutableStateFlow(NewStatusState())
    fun observeUi() = state.asStateFlow()

    fun changeStatus(id: Int, statusId: Int){
        viewModelScope.launch {
            try {
                dbRepository.changeStatus(id, statusId)
                state.update { it.copy(success = R.string.add_status_message, error = null) }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class NewStatusState(
    val success: Int? = null,
    val error: Int? = null
)