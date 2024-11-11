package com.picpay.desafio.android.userTest

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.ExampleService
import com.picpay.desafio.android.feature.userList.data.api.PicPayService
import com.picpay.desafio.android.feature.userList.data.model.UserRemote
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

class UserServiceTestApi {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    private var server = MockWebServer()


    @Test
    fun `when getUsers with emptyList returns empty list`() = runBlocking  {
        // given
        val expectedUsers = emptyList<UserRemote>()

        whenever(api.getUsers()).thenReturn(expectedUsers)

        // when
        val users = runBlocking { service.getUsers() }

        // then
        assertEquals(users, expectedUsers)
    }


    @Test
    fun `when getUsers returns users list`() = runBlocking {
        val expectedUsers = listOf(UserRemote(
            id = 1001,
            name = "Eduardo Santos",
            img = "https://randomuser.me/api/portraits/men/9.jpg",
            username = "@eduardo.santos"
        ))

        whenever(api.getUsers()).thenReturn(expectedUsers)

        val users = runBlocking { service.getUsers() }

        assertEquals(users, expectedUsers)
    }

    
}