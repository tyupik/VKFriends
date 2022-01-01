package ru.tyupik.vkfriends.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tyupik.vkfriends.viewmodel.LoginViewModel
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    @Singleton
    @Provides
    fun provideLoginViewModel() : LoginViewModel = LoginViewModel()
}