package com.example.shoplist.di

import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

//    @Provides
//    @Singleton
//    fun provideRepository(repository: RepositoryImpl) : Repository{
//        return repository
//    }


    // Якщо єдина мета - звʼязати інтерфейс з конкретною реалізацією, використовуємо анотацію @Binds
    @Binds
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository
}