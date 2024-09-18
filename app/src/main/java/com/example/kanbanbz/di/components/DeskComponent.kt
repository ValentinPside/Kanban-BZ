package com.example.kanbanbz.di.components

import com.example.kanbanbz.presentation.viewmodels.DeskViewModel
import dagger.Subcomponent

@Subcomponent
interface DeskComponent {
    fun viewModel(): DeskViewModel
}