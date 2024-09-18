package com.example.kanbanbz.di.modules

import com.example.kanbanbz.data.db.DbRepositoryImpl
import com.example.kanbanbz.domain.DbRepository
import dagger.Module
import dagger.Provides

@Module
object DbRepositoryModule {

    @Provides
    fun provideDbRepository(impl: DbRepositoryImpl): DbRepository = impl

}