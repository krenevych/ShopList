package com.example.shoplist.di

import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository() : Repository{
        return RepositoryImpl()
    }
}