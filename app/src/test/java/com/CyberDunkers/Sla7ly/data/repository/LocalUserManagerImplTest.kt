package com.CyberDunkers.Sla7ly.data.repository

import android.content.Context
import com.CyberDunkers.Sla7ly.domin.repository.SettingLocalDataSource
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class LocalUserManagerImplTest {


    lateinit var context: Context
    private lateinit var localUserManager: SettingLocalDataSource

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        context = mockk()
        localUserManager = LocalUserManagerImpl(context)
    }

    @Test
    fun `when enter the App store true value in preference`() = runBlocking {

        var output = false
        // arrange
         localUserManager.getAppEntry().collect{
              output = it
        }
        val expectations = true

        //act
        localUserManager.saveAppEntry()

        //assert
        TestCase.assertEquals(expectations , output)

    }

    @Test
    fun getAppEntry() {
    }

    @Test
    fun saveLoginState() {
    }

    @Test
    fun logout() {
    }

    @Test
    fun getLoginState() {
    }

    @Test
    fun getLocal() {
    }

    @Test
    fun setLocal() {
    }

    @Test
    fun getUserToken() {
    }

    @Test
    fun setUserToken() {
    }

    @Test
    fun delToken() {
    }
}