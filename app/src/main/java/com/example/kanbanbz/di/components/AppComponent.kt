package com.example.kanbanbz.di.components

import android.content.Context
import com.example.kanbanbz.di.modules.DbModule
import com.example.kanbanbz.di.modules.DbRepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DbModule::class,
        DbRepositoryModule::class
    ]
)
interface AppComponent {

    fun deskComponent(): DeskComponent

    fun taskComponent(): TaskComponent.TaskComponentFactory

    fun newTaskComponent(): NewTaskComponent

    fun newCommentComponent(): NewCommentComponent.NewCommentComponentFactory

    fun newStatusComponent(): ChangeStatusComponent.ChangeStatusComponentFactory

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}