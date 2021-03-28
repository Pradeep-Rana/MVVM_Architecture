package com.wipro.news.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wipro.news.app.viewmodel.AuthViewModel
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AuthViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var authViewModel: AuthViewModel

    @Before
    fun setUp() {
        println("before")
        authViewModel = AuthViewModel()
    }

    @Test
    fun getNewsListTest() {
        println("getNewsListTest called")
        assertNotNull(authViewModel.getNewsFeedList())
        assertNotNull(authViewModel.successNewsResponse)
        assertNotNull(authViewModel.successTitle)
        assertNotNull(authViewModel.successTitle)
        authViewModel.successNewsResponse.observeForever {
            println("getNewsListTest: ${it.size}")
        }
    }

    @Test
    fun getHistoryListTest() {
        println("getHistoryListTest called")
        assertNotNull(authViewModel.getHistoryVideoList())
        assertNotNull(authViewModel.historyList)
        authViewModel.historyList.observeForever {
            println("getHistoryListTest: ${it.size}")
        }
    }

    @After
    fun afterTest() {
        println("after")
    }
}