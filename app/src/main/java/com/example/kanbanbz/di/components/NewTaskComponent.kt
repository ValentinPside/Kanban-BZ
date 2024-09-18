package com.example.kanbanbz.di.components

import com.example.kanbanbz.presentation.viewmodels.NewTaskViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface NewTaskComponent {
    fun viewModel(): NewTaskViewModel
}