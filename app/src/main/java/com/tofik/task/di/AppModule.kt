package com.tofik.task.di

import android.content.Context
import android.content.SharedPreferences
import com.tofik.task.util.Constans
import com.tofik.task.util.SessionManagement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(Constans.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideEditor(preferences: SharedPreferences) = preferences.edit()


    @Singleton
    @Provides
    fun provideSessionManagement(preferences: SharedPreferences, editor: SharedPreferences.Editor) =
        SessionManagement(preferences, editor)



}