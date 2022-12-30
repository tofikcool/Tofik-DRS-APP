package com.tofik.task.di

import com.tofik.task.ui.home.representation.HomeRepository
import com.tofik.task.util.SessionManagement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@InstallIn(ApplicationComponent::class)
@Module
object RepositoriesModule {


    @Provides
    @Singleton
    fun provideHomeRepository(
         sessionManagement: SessionManagement
    ): HomeRepository {
        return HomeRepository(sessionManagement)
    }


}