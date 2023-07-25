package com.example.platformforschoolchildren.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.example.platformforschoolchildren.data.repositoryimpl.RegRepositoryImpl
import com.example.platformforschoolchildren.domain.repository.RegRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRegRepository(repo: RegRepositoryImpl): RegRepository
}