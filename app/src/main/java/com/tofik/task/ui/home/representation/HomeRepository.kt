package com.tofik.task.ui.home.representation

import com.tofik.task.util.SessionManagement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class HomeRepository @Inject constructor(
    private val sessionManagement: SessionManagement
) {






    fun logOutUser() {
        sessionManagement.clearUser()
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}