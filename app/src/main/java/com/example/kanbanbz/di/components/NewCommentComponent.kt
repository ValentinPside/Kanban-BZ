package com.example.kanbanbz.di.components

import com.example.kanbanbz.presentation.viewmodels.NewCommentViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface NewCommentComponent {
    fun viewModel(): NewCommentViewModel

    @Subcomponent.Factory
    interface NewCommentComponentFactory {
        fun create(
            @BindsInstance id: String
        ): NewCommentComponent
    }
}