package com.example.kanbanbz.di.components

import com.example.kanbanbz.presentation.viewmodels.NewCommentViewModel
import com.example.kanbanbz.presentation.viewmodels.NewStatusViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ChangeStatusComponent {
    fun viewModel(): NewStatusViewModel

    @Subcomponent.Factory
    interface ChangeStatusComponentFactory {
        fun create(
            @BindsInstance id: String
        ): ChangeStatusComponent
    }
}