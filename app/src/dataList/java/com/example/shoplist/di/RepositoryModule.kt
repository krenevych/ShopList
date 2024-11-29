package com.example.shoplist.di

import com.example.shoplist.data.RepositoryDataBase
import com.example.shoplist.data.RepositoryImpl
import com.example.shoplist.domain.Repository
import com.example.shoplist.hello
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
//    fun provideRepository(repository: RepositoryDataBase): Repository

//    fun callHello() {
//        hello()
//    }
}