package com.example.kanbanbz.di.components

import com.example.kanbanbz.presentation.viewmodels.TaskViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface TaskComponent {
    fun viewModel(): TaskViewModel

    @Subcomponent.Factory
    interface TaskComponentFactory {
        fun create(
            @BindsInstance id: String
        ): TaskComponent
    }
}