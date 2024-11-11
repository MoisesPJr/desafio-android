package com.picpay.desafio.android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.feature.userList.ui.UserListFragment
import com.picpay.desafio.android.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class UserListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private var server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext


    @Before
    fun setup() {
        hiltRule.inject()
        server.start(serverPort)

    }

    @Test
    fun shouldDisplayError() = runBlocking {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> errorResponse
                    else -> errorResponse
                }
            }
        }


        launchFragmentInHiltContainer<UserListFragment>()
        onView(withId(R.id.buttonRefresh)).perform(click())
        server.takeRequest()


        onView(withId(R.id.layoutError)).check(matches(isDisplayed()))


        server.close()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldDisplayTitle() {
        launchFragmentInHiltContainer<UserListFragment>()
        val expectedTitle = context.getString(R.string.title)
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItem() = runBlocking {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }
        launchFragmentInHiltContainer<UserListFragment>()

        server.takeRequest()



        RecyclerViewMatchers.checkRecyclerViewItem(
            resId = R.id.recyclerView,
            position = 0,
            withMatcher = withText("Eduardo Santos")
        )

        server.close()
    }


    companion object {
        private const val serverPort = 8080

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}