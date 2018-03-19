package fr.bessugesv.starwarschauffeurprive.triplist

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import android.widget.ProgressBar
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.TestHelpers
import fr.bessugesv.starwarschauffeurprive.api.StarWarsApi
import fr.bessugesv.starwarschauffeurprive.app.triplist.activity.TripListActivity
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vincent on 3/19/2018.
 */
@RunWith(AndroidJUnit4::class)
class TripListActivityTest {

    lateinit var server: MockWebServer
    @Rule @JvmField
    val activityRule: ActivityTestRule<TripListActivity> = ActivityTestRule<TripListActivity>(TripListActivity::class.java, false, false)

    @Before
    fun setup() {
        server = MockWebServer().also { it.start() }
        StarWarsApi.service = StarWarsApi.getService(server.url("/").toString())
        activityRule.launchActivity(null)
    }

    @Test
    fun networkError() {
        // waiting for server response, a progress bar should be displayed
        checkProgressBarVisibility(true)
        server.enqueue(MockResponse().setResponseCode(500))
        // got the response, the progress bar should be hidden
        checkProgressBarVisibility(false)
        // the response was an error, a reload button should be displayed
        checkReloadButtonVisibility(true)

    }

    @Test
    fun reloadAfterNetworkError () {
        // first there is an error
        server.enqueue(MockResponse().setResponseCode(500))
        // the user clicks on reload
        onView(withText("Reload")).perform(ViewActions.click())
        // the button should disappear and the progress bar appear
        checkReloadButtonVisibility(false)
        checkProgressBarVisibility(true)
        // this time it works
        TestHelpers.enqueueMockData(server, InstrumentationRegistry.getContext().assets.open("mockdata/trips.json"))
        // no button or progress bar should be displayed
        checkReloadButtonVisibility(false)
        checkProgressBarVisibility(false)
    }



    fun checkProgressBarVisibility(visible: Boolean) {
        Thread.sleep(50) // let the ui breathe
        onView(instanceOf(ProgressBar::class.java)).check(matches(if (visible) isDisplayed() else not(isDisplayed())))
    }

    fun checkReloadButtonVisibility(visible: Boolean) {
        Thread.sleep(50) // let the ui breathe
        onView(withText("Reload")).check(matches(allOf(
                if (visible) isDisplayed() else not(isDisplayed()),
                instanceOf(Button::class.java)
        )))
    }


}