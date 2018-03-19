package fr.bessugesv.starwarschauffeurprive.common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.widget.Button
import android.widget.ProgressBar
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.TestHelpers
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import kotlin.reflect.KClass

/**
 * Created by Vincent on 3/19/2018.
 */
abstract class ActivityTest<T : Activity>(val activityClass: KClass<T>) {

    lateinit var server: MockWebServer
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<T> = ActivityTestRule<T>(activityClass.java, false, false)
    open val extras: Bundle? = null

    @Before
    fun setup() {
        server = MockWebServer().also { it.start() }
        StarWarsApi.service = StarWarsApi.getService(server.url("/").toString())
        val intent = extras?.let {
            val intent = Intent(InstrumentationRegistry.getTargetContext(), activityClass.java)
            intent.putExtras(it)
        }
        activityRule.launchActivity(intent)
    }

    fun enqueueMockData(fileName: String) {
        TestHelpers.enqueueMockData(server, InstrumentationRegistry.getContext().assets.open("mockdata/$fileName"))
    }

    fun checkProgressBarVisibility(visible: Boolean) {
        Thread.sleep(100) // let the ui breathe
        Espresso.onView(Matchers.instanceOf(ProgressBar::class.java)).check(ViewAssertions.matches(if (visible) ViewMatchers.isDisplayed() else Matchers.not(ViewMatchers.isDisplayed())))
    }

    fun checkReloadButtonVisibility(visible: Boolean) {
        Thread.sleep(100) // let the ui breathe
        Espresso.onView(ViewMatchers.withText("Reload")).check(ViewAssertions.matches(Matchers.allOf(
                if (visible) ViewMatchers.isDisplayed() else Matchers.not(ViewMatchers.isDisplayed()),
                Matchers.instanceOf(Button::class.java)
        )))
    }

    fun checkUiBehaviorWhenServerError() {
        // waiting for server response, a progress bar should be displayed
        checkProgressBarVisibility(true)
        server.enqueue(MockResponse().setResponseCode(500))
        // got the response, the progress bar should be hidden
        checkProgressBarVisibility(false)
        // the response was an error, a reload button should be displayed
        checkReloadButtonVisibility(true)
    }

    fun checkReloadAfterServerError(mockFilePath: String) {
        // first there is an error
        server.enqueue(MockResponse().setResponseCode(500))
        // the user clicks on reload
        Espresso.onView(ViewMatchers.withText("Reload")).perform(ViewActions.click())
        // the button should disappear and the progress bar appear
        checkReloadButtonVisibility(false)
        checkProgressBarVisibility(true)
        // this time it works
        enqueueMockData(mockFilePath)
        // no button or progress bar should be displayed
        checkReloadButtonVisibility(false)
        checkProgressBarVisibility(false)
    }
}