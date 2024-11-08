package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runBlocking  {
        // given
        val expectedUsers = emptyList<UserRemote>()

        whenever(api.getUsers()).thenReturn(expectedUsers)

        // when
        val users = runBlocking { service.getUsers() }

        // then
        assertEquals(users, expectedUsers)
    }
}