package com.github.januprasad.userlist.di

import com.github.januprasad.userlist.repo.UsersRepository
import com.github.januprasad.userlist.repo.UsersRepositoryImpl
import com.github.januprasad.userlist.retro.APiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE = "https://dummyjson.com/"

@Module
@InstallIn(SingletonComponent::class)
class UsersDI {

    @Provides
    @Singleton
    fun provideApiService(): APiService {
        val retrofit = Retrofit
            .Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(APiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: APiService): UsersRepository {
        return UsersRepositoryImpl(apiService)
    }
}